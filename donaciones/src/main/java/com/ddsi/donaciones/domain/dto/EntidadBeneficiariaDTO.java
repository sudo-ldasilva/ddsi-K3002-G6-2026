package com.ddsi.donaciones.domain.dto;

import com.ddsi.donaciones.domain.Contacto;
import com.ddsi.donaciones.domain.Direccion;
import com.ddsi.donaciones.domain.EntidadBeneficiaria;

public class EntidadBeneficiariaDTO {
    private String razonSocial;
    private String tipo;
    private Contacto contacto;
    private Direccion direccion;

    public EntidadBeneficiariaDTO() {}

    public EntidadBeneficiariaDTO(String razonSocial, String tipo, Contacto contacto, Direccion direccion) {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.contacto = contacto;
        this.direccion = direccion;
    }

    public EntidadBeneficiariaDTO(EntidadBeneficiaria eb) {
        this.razonSocial = eb.getRazonSocial();
        this.tipo = eb.getTipo();
        this.contacto = eb.getContacto();
        this.direccion = eb.getDireccion();
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
