package com.backend_acr.springboot.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BackendAcrIwOApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendAcrIwOApplication.class, args);
	}

}
