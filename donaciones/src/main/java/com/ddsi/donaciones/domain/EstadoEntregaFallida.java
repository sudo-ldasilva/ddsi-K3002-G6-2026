package com.ddsi.donaciones.domain;

import java.util.Date;

public class EstadoEntregaFallida extends EstadoDonacion {
    private String justificacion;

    public EstadoEntregaFallida(Date fechaInicio, String justificacion) {
        super(EstadoDeDonacion.ENTREGA_FALLIDA, fechaInicio);
        this.justificacion = justificacion;
    }

    public String getJustificacion() {
        return justificacion;
    }
}
