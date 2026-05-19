package com.ddsi.donaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DonacionesApplication {

	public static void main(String[] args) {
		System.out.println("Hola, mundo!");
		SpringApplication.run(DonacionesApplication.class, args);
	}
}