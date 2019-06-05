package com.vinaypabba.herosapi;

import com.vinaypabba.herosapi.service.OverwatchApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Slf4j
public class HerosApiApplication {

	@Autowired
	private OverwatchApiService overwatchApiService;

	public static void main(String[] args) {
		SpringApplication.run(HerosApiApplication.class, args);
	}

	@PostConstruct
	private void init() {
		log.info("Initializing the H2 database with hero data");
		overwatchApiService.downloadData();
	}

}
