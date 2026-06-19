package com.ddsi.incentivos.domain;

import java.util.Date;

public class Insignia {
    private Date fechaCompletada;
    private Mision mision;

    public Insignia(Date fechaCompletada, Mision mision) {
        this.fechaCompletada = fechaCompletada;
        this.mision = mision;
    }

    public Date getFechaCompletada() {
        return fechaCompletada;
    }
}
