package com.loja.autos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutosApplication.class, args);
	}

}
