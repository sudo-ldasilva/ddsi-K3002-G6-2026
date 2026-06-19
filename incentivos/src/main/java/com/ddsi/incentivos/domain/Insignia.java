package com.ddsi.incentivos.domain;

public class Insignia {

    private Fecha fechaCompletada;
    private Mision mision;

    public Insignia(Fecha fechaCompletada, Mision mision) {
        this.fechaCompletada = fechaCompletada;
        this.mision = mision;
    }

    public Fecha getFechaCompletada() {
        return fechaCompletada;
    }
}
