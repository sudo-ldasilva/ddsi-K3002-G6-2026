package com.ddsi.donaciones.controller;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.ddsi.donaciones.domain.GestorDonaciones;
import com.ddsi.donaciones.domain.Donacion;
import com.ddsi.donaciones.domain.dto.DonacionDTO;

@RestController
@RequestMapping("/donantes")
public class DonacionController {

    @GetMapping
    public ResponseEntity<ArrayList<Donacion>> getDonantes() {
        return ResponseEntity.status(200).body(GestorDonaciones.getInstance().getDonaciones());
    }

    @PostMapping
    public ResponseEntity<Donacion> donar(@RequestBody Donacion donacion) {
        GestorDonaciones.getInstance().donar(donacion);
        return ResponseEntity.status(201).body(donacion);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<Donacion> actualizarDonacion(@PathVariable UUID uuid, @RequestBody DonacionDTO dto) {
        Donacion donacion = GestorDonaciones.getInstance().getDonacionByUUID(uuid);
        if (donacion == null) return ResponseEntity.status(404).body(null);

        if (dto.getDeposito() != null) {
            donacion.setDeposito(dto.getDeposito());
        }

        if (dto.getDonante() != null) {
            donacion.setDonante(dto.getDonante());
        }

        if (dto.getDescripcion() != null) {
            donacion.setDescripcion(dto.getDescripcion());
        }

        if (dto.getBienes() != null) {
            donacion.setBienes(dto.getBienes());
        }

        if (dto.yaFueSegmentada()) {
            donacion.margarSegmentada();
        }

        return ResponseEntity.status(200).body(donacion);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Donacion> eliminarDonacion(@PathVariable UUID uuid) {
        Donacion donacion = GestorDonaciones.getInstance().eliminarDonacionByUUID(uuid);
        return ResponseEntity.status( (donacion != null) ? 200 : 404).body(donacion);
    }
}
