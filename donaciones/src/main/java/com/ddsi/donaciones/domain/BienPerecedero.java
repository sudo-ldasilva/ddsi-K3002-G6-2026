package com.ddsi.donaciones.domain;

import java.time.LocalDate;
import java.util.Date;

public class BienPerecedero extends Bien {
    private Date fechaVencimiento;

    public BienPerecedero(String descripcion, String foto, Subcategoria subcategoria, Date fechaVencimiento) {
        super(descripcion, foto, subcategoria);
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean estaVencido() {
        return fechaVencimiento.before(new Date());
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }
}
