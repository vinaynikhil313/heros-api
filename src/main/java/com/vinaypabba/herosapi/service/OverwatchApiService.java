package com.vinaypabba.herosapi.service;

import com.vinaypabba.herosapi.dao.OverwatchDao;
import com.vinaypabba.herosapi.model.AllAbilitiesDto;
import com.vinaypabba.herosapi.model.AllHerosDto;
import com.vinaypabba.herosapi.model.HeroDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.Objects;

@Service
@Slf4j
public class OverwatchApiService {

    private static final String OVERWATCH_API_BASE_URL = "https://overwatch-api.net/api/v1";

    @Autowired
    private OverwatchDao overwatchDao;

    public void downloadData() {
        RestTemplate restTemplate = new RestTemplate();
        AllHerosDto allHeros = null;
        AllAbilitiesDto allAbilities = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity httpEntity = new HttpEntity(headers);
        try {
            ResponseEntity<AllHerosDto> responseEntity = restTemplate.exchange(URI.create(OVERWATCH_API_BASE_URL + "/hero"), HttpMethod.GET, httpEntity, AllHerosDto.class);
            if (HttpStatus.OK.equals(responseEntity.getStatusCode())
                    && Objects.nonNull(responseEntity.getBody())){
                allHeros = responseEntity.getBody();
            } else {
                throw new Exception("Http call unsuccessful. Status code - " + responseEntity.getStatusCodeValue());
            }
        } catch (Exception ex) {
            log.error("Error while fetching all heros", ex);
        }

        try {
            ResponseEntity<AllAbilitiesDto> responseEntity = restTemplate.exchange(URI.create(OVERWATCH_API_BASE_URL + "/ability"), HttpMethod.GET, httpEntity, AllAbilitiesDto.class);
            if (HttpStatus.OK.equals(responseEntity.getStatusCode())
                    && Objects.nonNull(responseEntity.getBody())){
                allAbilities = responseEntity.getBody();
            } else {
                throw new Exception("Http call unsuccessful. Status code - " + responseEntity.getStatusCodeValue());
            }
        } catch (Exception ex) {
            log.error("Error while fetching all abilities", ex);
        }

        if (Objects.nonNull(allAbilities)) {
            log.info("Saving all abilities to database");
            overwatchDao.saveAbilities(allAbilities.getData());
        }

        if (Objects.nonNull(allHeros)){
            allHeros.getData().forEach(hero -> {
                log.info("Fetching abilities for hero: " + hero.getName());
                try {
                    ResponseEntity<HeroDto> responseEntity = restTemplate.exchange(URI.create(OVERWATCH_API_BASE_URL + "/hero/" + hero.getId()),
                            HttpMethod.GET, httpEntity, HeroDto.class);
                    if (HttpStatus.OK.equals(responseEntity.getStatusCode())
                        && Objects.nonNull(responseEntity.getBody())){
                        hero.setAbilities(responseEntity.getBody().getAbilities());
                    } else {
                        throw new Exception("Http call unsuccessful. Status code - " + responseEntity.getStatusCodeValue());
                    }
                } catch (Exception ex) {
                    log.error("Error while fetching data for hero: " + hero.getName(), ex);
                }
            });
            log.info("Saving all heros to database");
            overwatchDao.saveHeros(allHeros.getData());
        }

    }

}
