package com.ddsi.donaciones.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

	@GetMapping
	public String index() {
		return "Health Check OK!"; // Devuelve un HTML con el texto
	}

}
