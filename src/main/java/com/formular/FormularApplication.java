package com.formular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FormularApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormularApplication.class, args);
	}

}
