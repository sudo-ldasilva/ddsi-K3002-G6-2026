package com.ddsi.donaciones.domain;

import java.time.LocalDate;

public class BienPerecedero extends Bien {
    private LocalDate fechaVencimiento;

    public BienPerecedero(String descripcion, String foto, Subcategoria subcategoria, LocalDate fechaVencimiento) {
        super(descripcion, foto, subcategoria);
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean estaVencido() {
        return fechaVencimiento.isBefore(LocalDate.now());
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }
}
