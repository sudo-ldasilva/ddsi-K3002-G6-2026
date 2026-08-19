package com.ddsi.donaciones.domain;

import java.time.LocalDate;

public class EstadoDonacion {
    protected EstadoDeDonacion estado;
    protected LocalDate fechaInicio ;

    public EstadoDonacion(EstadoDeDonacion estado, LocalDate fechaInicio) {
        this.estado = estado;
        this.fechaInicio = fechaInicio;
    }

    public EstadoDeDonacion getEstado() {
        return estado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
}
