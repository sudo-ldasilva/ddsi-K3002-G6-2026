package com.ddsi.donaciones.domain;

public class Representante{
    private Contacto contacto;

    public Representante(Contacto contacto) {
        this.contacto = contacto;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }
}
