package com.motomami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MotomamiApplication {
	public static void main(String[] args) {
		SpringApplication.run(MotomamiApplication.class, args);
	}
}
