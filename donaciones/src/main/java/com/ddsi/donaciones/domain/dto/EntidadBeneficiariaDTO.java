package com.ddsi.donaciones.domain.dto;

import com.ddsi.donaciones.domain.Contacto;
import com.ddsi.donaciones.domain.Direccion;


public class EntidadBeneficiariaDTO {
    private String razonSocial;
    private String tipo;
    private Contacto contacto;
    private Direccion direccion;

    public EntidadBeneficiariaDTO(String razonSocial, String tipo, Contacto contacto, Direccion direccion) {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.contacto = contacto;
        this.direccion = direccion;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getTipo() {
        return tipo;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public Direccion getDireccion() {
        return direccion;
    }
}
