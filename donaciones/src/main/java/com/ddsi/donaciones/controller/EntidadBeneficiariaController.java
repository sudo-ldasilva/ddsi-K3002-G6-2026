package com.ddsi.donaciones.controller;

import java.util.ArrayList;
import java.util.UUID;

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
    public ResponseEntity<EntidadBeneficiariaDTO> agregarEntidadBeneficiaria(@RequestBody EntidadBeneficiariaDTO entidad){
        GestorEntidadesBeneficiarias.getInstance().agregarEntidadBeneficiaria(entidad);
        return ResponseEntity.status(201).body(entidad);
    }

    @GetMapping("/{telefono}")
    public ResponseEntity<EntidadBeneficiaria> getEntidadBeneficiaria(@PathVariable String telefono){
        EntidadBeneficiaria entidadRegistrada = GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono);
        return ResponseEntity.status( (entidadRegistrada == null) ? 404 : 200).body(entidadRegistrada);
    }

    @PutMapping("/{telefono}")
    public ResponseEntity<EntidadBeneficiariaDTO> actualizarEntidadBeneficiaria(@PathVariable String telefono, @RequestBody EntidadBeneficiariaDTO entidadCambiada){
        EntidadBeneficiaria entidadRegistrada = GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono);
        if (entidadRegistrada == null) return ResponseEntity.status(404).body(null);

        entidadRegistrada.setRazonSocial(entidadCambiada.getRazonSocial());
        entidadRegistrada.setTipo(entidadCambiada.getTipo());
        entidadRegistrada.setDireccion(entidadCambiada.getDireccion());

        return ResponseEntity.status(201).body(entidadRegistrada.toDto());
    }

    @DeleteMapping("/{telefono}")
    public ResponseEntity<EntidadBeneficiaria> eliminarEntidadBeneficiaria(@PathVariable String telefono){
        EntidadBeneficiaria eliminada = GestorEntidadesBeneficiarias.getInstance().eliminarEntidad(telefono);
        return ResponseEntity.status((eliminada != null) ? 200 : 404).body(eliminada);
    }

    @GetMapping("/{telefono}/necesidades")
    public ResponseEntity<ArrayList<CampaniaNecesidadDTO>> getNecesidades(@PathVariable String telefono){
        ArrayList<CampaniaNecesidad> necesidades = GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono).getNecesidades();
        ArrayList<CampaniaNecesidadDTO> necesidadesDTO =  new ArrayList<>();
        for (CampaniaNecesidad nec : necesidades){
            necesidadesDTO.add(nec.toDTO());
        }
        return ResponseEntity.status(200).body(necesidadesDTO);
    }

    @PostMapping("/{telefono}/necesidadesRecurrentes")
    public ResponseEntity<EntidadBeneficiaria> subirCampaniaNecesidad(@PathVariable String telefono, @RequestBody CampaniaNecesidadRecurrente cnr){
        EntidadBeneficiaria eb = GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono);
        if (eb == null) return ResponseEntity.status(404).body(null);
        eb.agregarCampañaRecurrente(cnr);
        return ResponseEntity.status(201).body(eb);
    }

    @GetMapping("/{telefono}/necesidadesRecurrentes")
    public ResponseEntity<ArrayList<CampaniaNecesidadRecurrente>> getCampaniaNecesidad(@PathVariable String telefono){
        EntidadBeneficiaria eb = GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono);
        if (eb == null) return ResponseEntity.status(404).body(null);
        return ResponseEntity.status(201).body(eb.getCampañasRecurrentes());
    }

    @GetMapping("/{telefono}/necesidadesRecurrentes/{uuid}")
    public ResponseEntity<CampaniaNecesidadRecurrente> getCampaniaNecesidad(@PathVariable String telefono, @PathVariable UUID uuid){
        EntidadBeneficiaria eb = GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono);
        if (eb == null) return ResponseEntity.status(404).body(null);
        CampaniaNecesidadRecurrente cnr = eb.getCampañaRecurrente(uuid);
        return ResponseEntity.status( (cnr != null) ? 200 : 404 ).body(cnr);
    }

    @PostMapping("/{telefono}/necesidadesExtraordinarias")
    public ResponseEntity<CampaniaNecesidadDTO> crearCampaniaNecesidadExtraordinaria(@PathVariable String telefono, @RequestBody CampaniaNecesidadDTO campania){
        EntidadBeneficiaria eb = GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono);
        if (eb == null) return ResponseEntity.status(404).body(null);

        CampaniaNecesidadExtraordinaria campañaExtraordinaria = new CampaniaNecesidadExtraordinaria(campania, eb);
        eb.crearCampaniaNecesidad(campañaExtraordinaria);
        return ResponseEntity.status(201).body(campañaExtraordinaria.toDTO());
    }

    @GetMapping("/{telefono}/necesidades/{uuid}")
    public ResponseEntity<CampaniaNecesidadDTO> modificarCampaniaNecesidad(@PathVariable String telefono, @PathVariable UUID uuid){
        EntidadBeneficiaria eb = GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono);
        if (eb == null) return ResponseEntity.status(404).body(null);

        CampaniaNecesidad cn = eb.obtenerCampaniaNecesidad(uuid);
        return ResponseEntity.status((cn == null) ? 404 : 200).body((cn == null) ? null : cn.toDTO());
    }

    @DeleteMapping("/{telefono}/necesidades/{uuid}")
    public ResponseEntity<CampaniaNecesidadDTO> eliminarCampania(@PathVariable String telefono, @PathVariable UUID uuid){
        CampaniaNecesidad eliminada = GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono).eliminarCampaniaNecesidad(uuid);
        return ResponseEntity.status((eliminada != null) ? 200 : 404).body((eliminada == null) ? null : eliminada.toDTO());
    }
}
