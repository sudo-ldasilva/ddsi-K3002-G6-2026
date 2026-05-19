package com.ddsi.donaciones.domain;

import java.time.LocalDate;

public class BienPerecedero extends Bien {
    private int fechaVencimiento;

    public BienPerecedero(String descripcion, String foto, Subcategoria subcategoria, int fechaVencimiento) {
        super(descripcion, foto, subcategoria);
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean estaVencido() {
        int anioActual = LocalDate.now().getYear();
        return fechaVencimiento < anioActual;
    }

    public int getFechaVencimiento() {
        return fechaVencimiento;
    }
}
