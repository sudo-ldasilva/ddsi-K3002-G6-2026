package com.ddsi.donaciones.controller;

import java.util.ArrayList;
import java.util.UUID;

import com.ddsi.donaciones.domain.*;
import com.ddsi.donaciones.domain.dto.EntidadBeneficiariaDTO;
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
    public ResponseEntity<ArrayList<EntidadBeneficiariaDTO>> getEntidades(){
        ArrayList<EntidadBeneficiaria> entidades = GestorEntidadesBeneficiarias.getInstance().getEntidadesBeneficiarias();
        ArrayList<EntidadBeneficiariaDTO> entidadesDTO =  new ArrayList<>();
        for(EntidadBeneficiaria entidad : entidades){
            entidadesDTO.add(new EntidadBeneficiariaDTO(entidad.getRazonSocial(), entidad.getTipo(), entidad.getContacto(), entidad.getDireccion()));
        }
        return ResponseEntity.status(200).body(entidadesDTO);
    }

    @PostMapping
    public ResponseEntity<EntidadBeneficiaria> agregarEntidadBeneficiaria(@RequestBody EntidadBeneficiaria entidad){
        GestorEntidadesBeneficiarias.getInstance().agregarEntidadBeneficiaria(entidad);
        return ResponseEntity.status(201).body(entidad);
    }

    @PutMapping("/{telefono}")
    public ResponseEntity<EntidadBeneficiaria> actualizarEntidadBeneficiaria(@PathVariable String telefono, @RequestBody EntidadBeneficiaria entidadCambiada){
        EntidadBeneficiaria entidadRegistrada = GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono);

        entidadRegistrada.setRazonSocial(entidadCambiada.getRazonSocial());
        entidadRegistrada.setTipo(entidadCambiada.getTipo());
        entidadRegistrada.setDireccion(entidadCambiada.getDireccion());
        entidadRegistrada.setRepresentantes(entidadCambiada.getRepresentantes());
        entidadRegistrada.setNecesidades(entidadCambiada.getNecesidades());

        return ResponseEntity.status(201).body(entidadRegistrada);
    }

    @DeleteMapping("/{telefono}")
    public ResponseEntity<EntidadBeneficiaria> eliminarEntidadBeneficiaria(@PathVariable String telefono){
        EntidadBeneficiaria eliminada = GestorEntidadesBeneficiarias.getInstance().eliminarEntidad(telefono);
        return ResponseEntity.status((eliminada != null) ? 200 : 404).body(eliminada);
    }

    @GetMapping("/{telefono}/necesidades")
    public ResponseEntity<ArrayList<CampaniaNecesidad>> getNecesidades(@PathVariable String telefono){
        return ResponseEntity.status(200).body(GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono).getNecesidades());
    }

    @PostMapping("/{telefono}/necesidadesRecurrentes")
    public ResponseEntity<CampaniaNecesidad> crearCampaniaNecesidadRecurrente(@PathVariable String telefono, @RequestBody CampaniaNecesidadRecurrente campania){
        campania.setEntidadBeneficiaria(GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono));
        GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono).crearCampaniaNecesidad(campania);
        return ResponseEntity.status(201).body(campania);
    }

    @PutMapping("/{telefono}/necesidadesRecurrentes/{uuid}")
    public ResponseEntity<CampaniaNecesidad> modificarCampaniaNecesidad(@PathVariable String telefono, @PathVariable UUID uuid, @RequestBody CampaniaNecesidadRecurrente cambios){
        CampaniaNecesidadRecurrente campaniaRegistrada = (CampaniaNecesidadRecurrente) GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono).obtenerCampaniaNecesidad(uuid);
        //Agregar error en caso de que no sea una necesidad recurrente

        campaniaRegistrada.setNecesidades(cambios.getNecesidades());
        campaniaRegistrada.setEntidadBeneficiaria(cambios.getEntidadBeneficiaria());
        campaniaRegistrada.setDescripcion(cambios.getDescripcion());
        campaniaRegistrada.setEstado(cambios.getEstado());
        campaniaRegistrada.setPeriodo(cambios.getPeriodo());

        return ResponseEntity.status(201).body(campaniaRegistrada);
    }

    @PostMapping("/{telefono}/necesidadesExtraordinarias")
    public ResponseEntity<CampaniaNecesidad> crearCampaniaNecesidadExtraordinaria(@PathVariable String telefono, @RequestBody CampaniaNecesidadExtraordinaria campania){
        campania.setEntidadBeneficiaria(GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono));
        GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono).crearCampaniaNecesidad(campania);
        return ResponseEntity.status(201).body(campania);
    }

    @PutMapping("/{telefono}/necesidadesExtraordinarias/{uuid}")
    public ResponseEntity<CampaniaNecesidad> modificarCampaniaNecesidad(@PathVariable String telefono, @PathVariable UUID uuid, @RequestBody CampaniaNecesidadExtraordinaria cambios){
        CampaniaNecesidadExtraordinaria campaniaRegistrada = (CampaniaNecesidadExtraordinaria) GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono).obtenerCampaniaNecesidad(uuid);
        //Agregar error en caso de que no sea una necesidad extraordinaria

        campaniaRegistrada.setNecesidades(cambios.getNecesidades());
        campaniaRegistrada.setEntidadBeneficiaria(cambios.getEntidadBeneficiaria());
        campaniaRegistrada.setDescripcion(cambios.getDescripcion());
        campaniaRegistrada.setEstado(cambios.getEstado());
        campaniaRegistrada.setSituacionExcepcional(cambios.getSituacionExcepcional());

        return ResponseEntity.status(201).body(campaniaRegistrada);
    }

    @DeleteMapping("/{telefono}/necesidades/{uuid}")
    public ResponseEntity<CampaniaNecesidad> eliminarCampania(@PathVariable String telefono, @PathVariable UUID uuid){
        CampaniaNecesidad eliminada = GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono).eliminarCampaniaNecesidad(uuid);
        return ResponseEntity.status((eliminada != null) ? 200 : 404).body(eliminada);
    }
}