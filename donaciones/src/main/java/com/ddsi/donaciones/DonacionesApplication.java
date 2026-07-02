package com.ddsi.donaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DonacionesApplication {

	public static void main(String[] args) {
		System.out.println("Hola, mundo!");
		SpringApplication.run(DonacionesApplication.class, args);
	}
}
