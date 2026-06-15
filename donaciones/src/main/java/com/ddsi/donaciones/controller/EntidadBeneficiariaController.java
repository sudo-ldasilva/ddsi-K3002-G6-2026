package com.ddsi.donaciones.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.ddsi.donaciones.domain.GestorEntidadesBeneficiarias;
import com.ddsi.donaciones.domain.EntidadBeneficiaria;
import com.ddsi.donaciones.domain.Contacto;


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
        EntidadBeneficiaria eliminado = GestorEntidadesBeneficiarias.getInstance().eliminarEntidad(new Contacto(telefono, "Telefono"));
        return ResponseEntity.status((eliminado != null) ? 200 : 404).body(eliminado);
    }
}