package com.ddsi.donaciones.domain;

import com.ddsi.donaciones.domain.dto.CampaniaNecesidadDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CampaniaNecesidadPeriodo extends  CampaniaNecesidad {
    private LocalDate fechaInicio;
    private CampaniaNecesidadRecurrente campaniaRecurrente;

    public CampaniaNecesidadPeriodo(EntidadBeneficiaria entidadBeneficiaria, String descripcion, LocalDate fechaInicio, CampaniaNecesidadRecurrente campaniaRecurrente, ArrayList<NecesidadIndividual> necesidades) {
        super(entidadBeneficiaria, descripcion, necesidades, fechaInicio);
        this.campaniaRecurrente = campaniaRecurrente;
    }

    public CampaniaNecesidadRecurrente getCampaniaRecurrente() {
        return campaniaRecurrente;
    }

    @Override
    public CampaniaNecesidadDTO toDTO() {
        return new CampaniaNecesidadDTO(getUuid(),"Periodo",necesidades.stream().map( n -> n.toDTO() ).collect(Collectors.toCollection(ArrayList::new)),descripcion,fechaInicio,null);
    }
}
