package com.spring.guava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class GuavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuavaApplication.class, args);
	}
}
