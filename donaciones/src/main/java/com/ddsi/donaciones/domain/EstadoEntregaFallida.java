package com.ddsi.donaciones.domain;

import java.time.LocalDate;

public class EstadoEntregaFallida extends EstadoDonacion {
    private String justificacion;

    public EstadoEntregaFallida(LocalDate fechaInicio, String justificacion) {
        super(EstadoDeDonacion.ENTREGA_FALLIDA, fechaInicio);
        this.justificacion = justificacion;
    }

    public String getJustificacion() {
        return justificacion;
    }
}
