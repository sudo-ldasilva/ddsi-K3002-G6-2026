package com.ddsi.donaciones.domain.dto;

import com.ddsi.donaciones.domain.Direccion;

public class EntidadBeneficiariaDTO {
    private String razonSocial;
    private String tipo;
    private String telefono;
    private Direccion direccion;

    public EntidadBeneficiariaDTO() {}

    public EntidadBeneficiariaDTO(String razonSocial, String tipo, String telefono, Direccion direccion) {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getTipo() {
        return tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }
}
