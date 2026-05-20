package com.ddsi.donaciones.domain;

public abstract class Contacto {
    private String direccion;

    public Contacto(String direccion) {
        this.direccion = direccion;
    }

    public String formatearDireccion() {
        return direccion;
    }
}
