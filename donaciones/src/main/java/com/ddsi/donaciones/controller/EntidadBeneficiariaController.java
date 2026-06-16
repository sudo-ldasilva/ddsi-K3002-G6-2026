package com.ddsi.donaciones.controller;

import java.util.ArrayList;
import java.util.UUID;

import com.ddsi.donaciones.domain.*;
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
@RequestMapping("/entidadesBeneficiarias")
public class EntidadBeneficiariaController{

    @GetMapping
    public ResponseEntity<ArrayList<EntidadBeneficiaria>> getEntidades(){
        return ResponseEntity.status(200).body(GestorEntidadesBeneficiarias.getInstance().getEntidadesBeneficiarias());
    }

    @PostMapping
    public ResponseEntity<EntidadBeneficiaria> agregarEntidadBeneficiaria(@RequestBody EntidadBeneficiaria entidad){
        GestorEntidadesBeneficiarias.getInstance().agregarEntidadBeneficiaria(entidad);
        return ResponseEntity.status(201).body(entidad);
    }

    @PutMapping("/{telefono}")
    public ResponseEntity<EntidadBeneficiaria> actualizarEntidadBeneficiaria(@PathVariable String telefono, @RequestBody EntidadBeneficiaria entidadCambiada){
        EntidadBeneficiaria entidadRegistrada = GestorEntidadesBeneficiarias.getInstance().getEntidad(new Contacto(telefono, "Telefono"));

        entidadRegistrada.setRazonSocial(entidadCambiada.getRazonSocial());
        entidadRegistrada.setTipo(entidadCambiada.getTipo());
        entidadRegistrada.setDireccion(entidadCambiada.getDireccion());
        entidadRegistrada.setRepresentantes(entidadCambiada.getRepresentantes());
        entidadRegistrada.setNecesidades(entidadCambiada.getNecesidades());

        return ResponseEntity.status(201).body(entidadRegistrada);
    }

    @DeleteMapping("/{telefono}")
    public ResponseEntity<EntidadBeneficiaria> eliminarEntidadBeneficiaria(@PathVariable String telefono){
        EntidadBeneficiaria eliminada = GestorEntidadesBeneficiarias.getInstance().eliminarEntidad(new Contacto(telefono, "Telefono"));
        return ResponseEntity.status((eliminada != null) ? 200 : 404).body(eliminada);
    }

    @GetMapping("/{telefono}/necesidades")
    public ResponseEntity<ArrayList<CampaniaNecesidad>> getNecesidades(@PathVariable String telefono){
        return ResponseEntity.status(200).body(GestorEntidadesBeneficiarias.getInstance().getEntidad(new Contacto(telefono, "Telefono")).getNecesidades());
    }

    @PostMapping("/{telefono}/necesidades")
    public ResponseEntity<CampaniaNecesidad> crearCampaniaNecesidad(@PathVariable String telefono, @RequestBody CampaniaNecesidad campania){
        GestorEntidadesBeneficiarias.getInstance().getEntidad(new Contacto(telefono, "Telefono")).crearCampaniaNecesidad(campania);
        return ResponseEntity.status(201).body(campania);
    }

    @PutMapping("/{telefono}/necesidades/{uuid}")
    public ResponseEntity<CampaniaNecesidad> modificarCampaniaNecesidad(@PathVariable String telefono, @PathVariable UUID uuid, @RequestBody CampaniaNecesidad cambios){
        CampaniaNecesidad campaniaRegistrada = GestorEntidadesBeneficiarias.getInstance().getEntidad(new Contacto(telefono, "Telefono")).obtenerCampaniaNecesidad(uuid);

        campaniaRegistrada.setNecesidades(cambios.getNecesidades());
        campaniaRegistrada.setEntidadBeneficiaria(cambios.getEntidadBeneficiaria());
        campaniaRegistrada.setDescripcion(cambios.getDescripcion());
        campaniaRegistrada.setEstado(cambios.getEstado());

        return ResponseEntity.status(201).body(campaniaRegistrada);
    }

    @DeleteMapping("/{telefono}/necesidades/{uuid}")
    public ResponseEntity<CampaniaNecesidad> eliminarCampania(@PathVariable String telefono, @PathVariable UUID uuid){
        CampaniaNecesidad eliminada = GestorEntidadesBeneficiarias.getInstance().getEntidad(new Contacto(telefono, "Telefono")).eliminarCampaniaNecesidad(uuid);
        return ResponseEntity.status((eliminada != null) ? 200 : 404).body(eliminada);
    }
}