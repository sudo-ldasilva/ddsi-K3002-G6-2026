package com.ddsi.donaciones.controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.ddsi.donaciones.domain.*;
import com.ddsi.donaciones.domain.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/donantes")
public class DonanteController {

    @GetMapping
    public ResponseEntity<ArrayList<Donante>> getDonantes() {
        return ResponseEntity.status(200).body(GestorDonantes.getInstance().getDonantes());
    }

    @GetMapping("/{mail}/independientesPorMail")
    public ResponseEntity<ArrayList<DonacionIndependienteDTO>> getDonacionIndependientes(@PathVariable String mail) {
        ArrayList<DonacionIndependienteDTO> donaciones = GestorDonaciones.getInstance()
                .getDonacionesIndependientes()
                .stream()
                .filter(d -> d.getDonacion().getDonante().getMail().equalsIgnoreCase(mail))
                .map(d -> d.toDto())
                .collect(Collectors.toCollection(ArrayList::new));
        return ResponseEntity.status(200).body(donaciones);
    }

    @GetMapping("/{mail}/contactos")
    public ResponseEntity<ArrayList<Contacto>> getContactos(@PathVariable String mail) {
        Donante donante = GestorDonantes.getInstance().getDonante(mail);
        if (donante == null) return ResponseEntity.status(404).body(null);
        return ResponseEntity.status(200).body(donante.getMediosDeContacto());
    }

    @PostMapping("/{mail}/contactos")
    public ResponseEntity<ArrayList<Contacto>> postContactos(@PathVariable String mail, @RequestBody Contacto contacto) {
        Donante donante = GestorDonantes.getInstance().getDonante(mail);
        if (donante == null) return ResponseEntity.status(404).body(null);
        donante.agregarContacto(contacto);
        return ResponseEntity.status(200).body(donante.getMediosDeContacto());
    }

    @PostMapping("/humanos")
    public ResponseEntity<PersonaHumana> crearHumano(@RequestBody PersonaHumana donante) {
        GestorDonantes.getInstance().registrarDonante(donante);
        return ResponseEntity.status(201).body(donante);
    }

    @PostMapping("/juridicos")
    public ResponseEntity<PersonaJuridica> crearJuridico(@RequestBody PersonaJuridicaDTO donante) {
        PersonaJuridica pj = new PersonaJuridica(donante);
        GestorDonantes.getInstance().registrarDonante(pj);
        return ResponseEntity.status(201).body(pj);
    }

    @PutMapping("/humanos/{mail}")
    public ResponseEntity<PersonaHumana> modificarHumano(@PathVariable String mail, @RequestBody PersonaHumana cambios) {
        Donante donante = GestorDonantes.getInstance().getDonante(mail);

        if ( !(donante instanceof PersonaHumana) ) {
            return ResponseEntity.status(404).body(null);
        }

        PersonaHumana humana = (PersonaHumana) donante;
        humana.setNombreYApellido(cambios.getNombreYApellido());
        humana.setEdad(cambios.getEdad());
        humana.setGenero(cambios.getGenero());
        humana.setDireccion(cambios.getDireccion());
        try {
            humana.setMedioPredeterminado(cambios.getMedioPredeterminado());
        } catch (Exception e) {
            throw new Error("Medio predeterminado no está incluido dentro de los medios de contacto");
        }

        return ResponseEntity.status(201).body(humana);
    }

    @PutMapping("/juridicos/{mail}")
    public ResponseEntity<PersonaJuridicaDTO> modificarJuridico(@PathVariable String mail, @RequestBody PersonaJuridicaDTO cambios) {
        Donante donante = GestorDonantes.getInstance().getDonante(mail);

        if ( donante == null ) return ResponseEntity.status(404).body(null);
        if ( !(donante instanceof PersonaJuridica) ) return ResponseEntity.status(400).body(null);

        PersonaJuridica juridica = (PersonaJuridica) donante;

        juridica.setMediosDeContacto(cambios.getMediosDeContacto());
        juridica.setDocumento(cambios.getDocumento());
        juridica.setRazonSocial(cambios.getRazonSocial());
        juridica.setTipo(cambios.getTipo());
        juridica.setRubro(cambios.getRubro());
        // TODO Tendríamos que crear un end-point de representantes

        return ResponseEntity.status(201).body(juridica.toDTO());
    }

    @DeleteMapping("/{mail}")
    public ResponseEntity<Donante> deleteDonante(@PathVariable String mail) {
        Donante eliminado = GestorDonantes.getInstance().eliminarDonante(mail);
        return ResponseEntity.status((eliminado != null) ? 200 : 404).body(eliminado);
    }
}
