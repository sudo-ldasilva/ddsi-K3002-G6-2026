package com.ddsi.donaciones.domain;

import java.util.ArrayList;

public abstract class Donante {
    protected Contacto mail;
    protected ArrayList<Contacto> mediosDeContacto;
    protected Documento documento;

    public Donante(Contacto mail, Documento documento) {
        this.mail = mail;
        this.mediosDeContacto = new ArrayList<>();
        this.documento = documento;
    }

    public Contacto getMail() {
        return this.mail;
    }

    public ArrayList<Contacto> getContactos() {
        return mediosDeContacto;
    }

    public void agregarContacto(Contacto contacto) {
        this.mediosDeContacto.add(contacto);
    }

    public void eliminarContacto(Contacto contacto) {
        this.mediosDeContacto.remove(contacto);
    }

    public void setMediosDeContacto(ArrayList<Contacto> mediosDeContacto) {
        this.mediosDeContacto = mediosDeContacto;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Documento getDocumento() {
        return this.documento;
    }

    public boolean tienEsteMail(Contacto contacto) {
        if (this.mail != null &&
                "mail".equalsIgnoreCase(this.mail.getTipoContacto()) &&
                this.mail.getDireccion().equalsIgnoreCase(contacto.getDireccion())) {
            return true;
        }
        return mediosDeContacto.stream()
                .filter(c -> "mail".equalsIgnoreCase(c.getTipoContacto()))
                .anyMatch(c -> c.getDireccion().equalsIgnoreCase(contacto.getDireccion()));
    }
}