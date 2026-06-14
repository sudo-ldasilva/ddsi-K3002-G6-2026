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

    @PatchMapping("/humanos/{uuid}")
    public ResponseEntity<Donante> modificarHumano(@PathVariable UUID uuid, @RequestBody PersonaHumana cambios) {
        Donante donante = GestorDonantes.getInstance().getDonante(uuid);

        if ( !(donante instanceof PersonaHumana) ) {
            return ResponseEntity.status(404).body(null);
        }

        PersonaHumana humana = (PersonaHumana) donante;

        if (cambios.getContactos() != null) {
            humana.setMediosDeContacto(cambios.getContactos());
        }

        if (cambios.getDocumento() != null) {
            humana.setDocumento(cambios.getDocumento());
        }

        if (cambios.getNombreYApellido() != null) {
            humana.setNombreYApellido(cambios.getNombreYApellido());
        }
        if (cambios.getEdad() != 0) {
            humana.setEdad(cambios.getEdad());
        }
        if (cambios.getGenero() != null) {
            humana.setGenero(cambios.getGenero());
        }
        if (cambios.getDireccion() != null) {
            humana.setDireccion(cambios.getDireccion());
        }
        if (cambios.getMedioPredeterminado() != null) {
            try {
                humana.setMedioPredeterminado(cambios.getMedioPredeterminado());
            } catch (Exception e) {
                return ResponseEntity.status(400).body(null); // TODO Mostrar mensaje de error (?)
            }
        }

        GestorDonantes.getInstance().registrarDonante(donante);
        return ResponseEntity.status(201).body(donante);
    }

    @PatchMapping("/juridicos/{uuid}")
    public ResponseEntity<Donante> modificarJuridico(@PathVariable UUID uuid, @RequestBody PersonaJuridica cambios) {
        Donante donante = GestorDonantes.getInstance().getDonante(uuid);

        if ( !(donante instanceof PersonaJuridica) ) {
            return ResponseEntity.status(404).body(null);
        }

        PersonaJuridica juridica = (PersonaJuridica) donante;

        if (cambios.getContactos() != null) {
            juridica.setMediosDeContacto(cambios.getContactos());
        }

        if (cambios.getDocumento() != null) {
            juridica.setDocumento(cambios.getDocumento());
        }

        if (cambios.getRazonSocial() != null) {
            juridica.setRazonSocial(cambios.getRazonSocial());
        }

        if (cambios.getTipo() != null) {
            juridica.setTipo(cambios.getTipo());
        }

        if (cambios.getRubro() != null) {
            juridica.setRubro(cambios.getRubro());
        }

        if (cambios.getRepresentantes() != null) {
            juridica.setRepresentantes(cambios.getRepresentantes());
        }

        GestorDonantes.getInstance().registrarDonante(donante);
        return ResponseEntity.status(201).body(donante);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Donante> deleteDonante(@PathVariable UUID uuid) {
        Donante eliminado = GestorDonantes.getInstance().eliminarDonante(uuid);
        return ResponseEntity.status((eliminado != null) ? 200 : 404).body(eliminado);
    }
}
