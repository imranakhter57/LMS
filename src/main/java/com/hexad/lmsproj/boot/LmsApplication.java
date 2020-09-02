package com.hexad.lmsproj.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.hexad.lmsproj")
@EnableScheduling
public class LmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.hexad.lmsproj.boot.LmsApplication.class, args);
	}
	}
