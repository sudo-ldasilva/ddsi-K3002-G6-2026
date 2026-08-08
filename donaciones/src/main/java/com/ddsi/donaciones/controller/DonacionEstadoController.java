package com.ddsi.donaciones.controller;

import java.util.Date;
import java.util.UUID;

import com.ddsi.donaciones.domain.*;
import com.ddsi.donaciones.domain.dto.DonacionIndependienteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/donaciones/independientes/{uuid}/estado")
public class DonacionEstadoController {
    @PutMapping
    public ResponseEntity<DonacionIndependienteDTO> actualizarEstadoDonacionIndependiente(@PathVariable UUID uuid, @RequestBody EstadoDeDonacion estado) {
        DonacionIndependiente donacion = GestorDonaciones.getInstance().getDonacionIndependienteByUUID(uuid);
        if (estado == null) return ResponseEntity.status(404).body(null);

        donacion.cambiarEstado(new EstadoDonacion(estado, new Date()));

        return ResponseEntity.status(200).body(donacion.toDto());
    }
}
