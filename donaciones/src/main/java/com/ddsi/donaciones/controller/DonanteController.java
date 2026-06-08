package com.ddsi.donaciones.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddsi.donaciones.domain.GestorDonantes;
import com.ddsi.donaciones.domain.PersonaHumana;
import com.ddsi.donaciones.domain.PersonaJuridica;
import com.ddsi.donaciones.domain.Donante;

@RestController
@RequestMapping("/donantes")
public class DonanteController {

    @PostMapping("/humanos")
    public String crearHumano(@RequestBody PersonaHumana donante) {
		GestorDonantes.getInstance().registrarDonante(donante);
        return "Persona humana recibida correctamente";
    }

    @PostMapping("/juridicos")
    public String crearJuridico(@RequestBody PersonaJuridica donante) {
		GestorDonantes.getInstance().registrarDonante(donante);
        return "Persona juridica recibida correctamente";
    }

	@GetMapping
	public ArrayList<Donante> getDonantes() {
		return GestorDonantes.getInstance().getDonantes();
	}
}
