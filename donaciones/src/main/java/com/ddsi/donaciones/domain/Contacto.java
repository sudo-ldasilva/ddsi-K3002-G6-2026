package com.ddsi.donaciones.domain;

public class Contacto {
    private String direccion;
    private String tipoContacto;

    public Contacto(String direccion, String tipoContacto) {
        this.direccion = direccion;
        this.tipoContacto = tipoContacto;
    }

    public String getDireccion() {
        return direccion;
    }
}
