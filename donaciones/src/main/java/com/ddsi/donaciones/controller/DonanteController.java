package com.ddsi.donaciones.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<ArrayList<Donante>> getDonantes() {
        return ResponseEntity.status(200).body(GestorDonantes.getInstance().getDonantes());
    }

    @PostMapping("/humanos")
    public ResponseEntity<Donante> crearHumano(@RequestBody PersonaHumana donante) {
        GestorDonantes.getInstance().registrarDonante(donante);
        return ResponseEntity.status(201).body(donante);
    }

    @PostMapping("/juridicos")
    public ResponseEntity<Donante> crearJuridico(@RequestBody PersonaJuridica donante) {
        GestorDonantes.getInstance().registrarDonante(donante);
        return ResponseEntity.status(201).body(donante);
    }
}
