package com.ddsi.donaciones.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

	@GetMapping("/health")
	public String index() {
		return "Health Check OK!"; // Devuelve un HTML con el texto
	}

}
