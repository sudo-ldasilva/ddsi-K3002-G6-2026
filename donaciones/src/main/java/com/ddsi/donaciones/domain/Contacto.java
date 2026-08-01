package com.ddsi.donaciones.domain;

import java.util.Objects;

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
    public String getTipoContacto() {
        return tipoContacto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacto contacto = (Contacto) o;

        return Objects.equals(direccion, contacto.direccion)
                && Objects.equals(tipoContacto, contacto.tipoContacto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direccion, tipoContacto);
    }
}
