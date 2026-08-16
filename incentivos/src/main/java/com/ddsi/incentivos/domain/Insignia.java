package com.ddsi.incentivos.domain;

import java.time.LocalDate;

public class Insignia {
    private LocalDate fechaCompletada;
    private Mision mision;

    public Insignia(LocalDate fechaCompletada, Mision mision) {
        this.fechaCompletada = fechaCompletada;
        this.mision = mision;
    }

    public LocalDate getFechaCompletada() {
        return fechaCompletada;
    }

    public Mision getMision() {
        return this.mision;
    }
}
