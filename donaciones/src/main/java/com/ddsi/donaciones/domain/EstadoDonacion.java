package com.ddsi.donaciones.domain;

import java.util.Date;

public class EstadoDonacion {
    protected EstadoDeDonacion estado;
    protected Date fechaInicio ;

    public EstadoDonacion(EstadoDeDonacion estado, Date fechaInicio) {
        this.estado = estado;
        this.fechaInicio = fechaInicio;
    }

    public EstadoDeDonacion getEstado() {
        return estado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }
}
