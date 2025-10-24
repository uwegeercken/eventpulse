package com.datamelt.eventpulse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication
{

	private static final Logger logger = LoggerFactory.getLogger(MainApplication.class);

	public static void main(String[] args)
	{
		SpringApplication.run(MainApplication.class, args);

		//logger.info("welcome to: {}", config.getName() + " " + config.getVersion() + ", last updated: " + config.getDate());

	}

}
