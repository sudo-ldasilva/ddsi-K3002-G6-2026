package com.ddsi.donaciones.domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class CampaniaNecesidadPeriodo extends  CampaniaNecesidad {
    private LocalDate fechaInicio;
    private CampaniaNecesidadRecurrente campaniaRecurrente;

    public CampaniaNecesidadPeriodo(EntidadBeneficiaria entidadBeneficiaria, String descripcion, LocalDate fechaInicio, CampaniaNecesidadRecurrente campaniaRecurrente, ArrayList<NecesidadIndividual> necesidades) {
        super(entidadBeneficiaria, descripcion,  necesidades);
        this.fechaInicio = fechaInicio;
        this.campaniaRecurrente = campaniaRecurrente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public CampaniaNecesidadRecurrente getCampaniaRecurrente() {
        return campaniaRecurrente;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}
