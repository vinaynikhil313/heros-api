package com.vinaypabba.herosapi.dao;

import com.vinaypabba.herosapi.model.AbilityDto;
import com.vinaypabba.herosapi.model.HeroDto;
import com.vinaypabba.herosapi.repo.AbilityRepository;
import com.vinaypabba.herosapi.repo.HeroRepository;
import com.vinaypabba.herosapi.repo.entity.Ability;
import com.vinaypabba.herosapi.repo.entity.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OverwatchDao {

    @Autowired
    private AbilityRepository abilityRepository;

    @Autowired
    private HeroRepository heroRepository;

    public void saveAbilities(List<AbilityDto> abilities) {
        List<Ability> abilityEntityList = new ArrayList<>();
        abilities.forEach(ability -> abilityEntityList.add(ability.getAbilityEntity()));
        abilityRepository.saveAll(abilityEntityList);
    }

    public void saveHeros(List<HeroDto> heros) {
        List<Hero> heroEntityList = new ArrayList<>();
        heros.forEach(hero -> heroEntityList.add(hero.getHeroEntity()));
        heroRepository.saveAll(heroEntityList);
    }

}
