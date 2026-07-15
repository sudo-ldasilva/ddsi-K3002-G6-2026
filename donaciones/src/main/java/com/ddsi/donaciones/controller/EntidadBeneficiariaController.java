package com.ddsi.donaciones.controller;

import java.util.ArrayList;
import java.util.UUID;

import com.ddsi.donaciones.domain.*;
import com.ddsi.donaciones.domain.dto.EntidadBeneficiariaDTO;
import com.ddsi.donaciones.domain.dto.CampaniaNecesidadDTO;
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

        entidadRegistrada.setRazonSocial(entidadCambiada.getRazonSocial());
        entidadRegistrada.setTipo(entidadCambiada.getTipo());
        entidadRegistrada.setDireccion(entidadCambiada.getDireccion());

        return ResponseEntity.status(201).body(new EntidadBeneficiariaDTO(entidadRegistrada));
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
        for(CampaniaNecesidad nec : necesidades){
            CampaniaNecesidadDTO necDTO = null;
            if(nec instanceof CampaniaNecesidadPeriodo){
                necDTO = new CampaniaNecesidadDTO(nec.getUuid(), "Recurrente", nec.getNecesidades(), nec.getDescripcion(), ((CampaniaNecesidadPeriodo) nec).getFechaInicio(), null);
            } else if (nec instanceof CampaniaNecesidadExtraordinaria){
                necDTO = new CampaniaNecesidadDTO(nec.getUuid(), "Extraordinaria", nec.getNecesidades(), nec.getDescripcion(), null, ((CampaniaNecesidadExtraordinaria) nec).getSituacionExcepcional());
            }
            necesidadesDTO.add(necDTO);
        }
        return ResponseEntity.status(200).body(necesidadesDTO);
    }

    @PostMapping("/{telefono}/necesidadesPeriodo")
    public ResponseEntity<CampaniaNecesidad> crearCampaniaNecesidadRecurrente(@PathVariable String telefono, @RequestBody CampaniaNecesidadPeriodo campania){
        campania.setEntidadBeneficiaria(GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono));
        GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono).crearCampaniaNecesidad(campania);
        return ResponseEntity.status(201).body(campania);
    }

    @PutMapping("/{telefono}/necesidadesPeriodo/{uuid}")
    public ResponseEntity<CampaniaNecesidad> modificarCampaniaNecesidad(@PathVariable String telefono, @PathVariable UUID uuid, @RequestBody CampaniaNecesidadPeriodo cambios){
        if(!(GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono).obtenerCampaniaNecesidad(uuid) instanceof CampaniaNecesidadPeriodo)){
            return ResponseEntity.status(404).body(cambios);
        }

        CampaniaNecesidadPeriodo campaniaRegistrada = (CampaniaNecesidadPeriodo) GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono).obtenerCampaniaNecesidad(uuid);

        campaniaRegistrada.setNecesidades(cambios.getNecesidades());
        campaniaRegistrada.setEntidadBeneficiaria(cambios.getEntidadBeneficiaria());
        campaniaRegistrada.setDescripcion(cambios.getDescripcion());
        campaniaRegistrada.setFechaInicio(cambios.getFechaInicio());

        return ResponseEntity.status(201).body(campaniaRegistrada);
    }

    @PostMapping("/{telefono}/necesidadesRecurrentes")
    public ResponseEntity<EntidadBeneficiaria> subirCampaniaNecesidad(@PathVariable String telefono, @RequestBody CampaniaNecesidadRecurrente cnr){
        EntidadBeneficiaria eb = GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono);
        eb.agregarCampañaRecurrente(cnr);
        return ResponseEntity.status(201).body(eb);
    }

    @GetMapping("/{telefono}/necesidadesRecurrentes/{uuid}")
    public ResponseEntity<CampaniaNecesidadRecurrente> getCampaniaNecesidad(@PathVariable String telefono, @PathVariable UUID uuid){
        EntidadBeneficiaria eb = GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono);
        CampaniaNecesidadRecurrente cnr = eb.getCampañaRecurrente(uuid);
        return ResponseEntity.status(201).body(cnr);
    }

    @PostMapping("/{telefono}/necesidadesExtraordinarias")
    public ResponseEntity<CampaniaNecesidad> crearCampaniaNecesidadExtraordinaria(@PathVariable String telefono, @RequestBody CampaniaNecesidadExtraordinaria campania){
        campania.setEntidadBeneficiaria(GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono));
        GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono).crearCampaniaNecesidad(campania);
        return ResponseEntity.status(201).body(campania);
    }

    @PutMapping("/{telefono}/necesidadesExtraordinarias/{uuid}")
    public ResponseEntity<CampaniaNecesidad> modificarCampaniaNecesidad(@PathVariable String telefono, @PathVariable UUID uuid, @RequestBody CampaniaNecesidadExtraordinaria cambios){
        if(!(GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono).obtenerCampaniaNecesidad(uuid) instanceof CampaniaNecesidadExtraordinaria)){
            return ResponseEntity.status(404).body(cambios);
        }

        CampaniaNecesidadExtraordinaria campaniaRegistrada = (CampaniaNecesidadExtraordinaria) GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono).obtenerCampaniaNecesidad(uuid);

        campaniaRegistrada.setNecesidades(cambios.getNecesidades());
        campaniaRegistrada.setEntidadBeneficiaria(cambios.getEntidadBeneficiaria());
        campaniaRegistrada.setDescripcion(cambios.getDescripcion());
        campaniaRegistrada.setSituacionExcepcional(cambios.getSituacionExcepcional());

        return ResponseEntity.status(201).body(campaniaRegistrada);
    }

    @DeleteMapping("/{telefono}/necesidades/{uuid}")
    public ResponseEntity<CampaniaNecesidad> eliminarCampania(@PathVariable String telefono, @PathVariable UUID uuid){
        CampaniaNecesidad eliminada = GestorEntidadesBeneficiarias.getInstance().getEntidad(telefono).eliminarCampaniaNecesidad(uuid);
        return ResponseEntity.status((eliminada != null) ? 200 : 404).body(eliminada);
    }
}
