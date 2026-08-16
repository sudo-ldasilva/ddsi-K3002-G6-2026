package com.ddsi.donaciones.domain;

import java.util.ArrayList;

public abstract class Donante {
    protected String mail;
    protected ArrayList<Contacto> mediosDeContacto;
    protected Documento documento;

    public Donante(String mail, Documento documento) {
        this.mail = mail;
        this.documento = documento;

        this.mediosDeContacto = new ArrayList<>();
        mediosDeContacto.add(new Contacto(mail, "mail"));
    }

    public String getMail() {
        return this.mail;
    }

    public ArrayList<Contacto> getMediosDeContacto() {
        return mediosDeContacto;
    }

    public void agregarContacto(Contacto contacto) {
        if (!this.mediosDeContacto.contains(contacto)) {
            this.mediosDeContacto.add(contacto);
        }
    }

    public void eliminarContacto(Contacto contacto) {
        this.mediosDeContacto.remove(contacto);
    }

    public void setMediosDeContacto(ArrayList<Contacto> mediosDeContacto) {
        this.mediosDeContacto = mediosDeContacto;

        Contacto mailContacto = new Contacto(mail, "mail");
        if (!this.mediosDeContacto.contains(mailContacto)) {
            mediosDeContacto.add(mailContacto);
        }
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Documento getDocumento() {
        return this.documento;
    }

    public boolean tieneContacto(Contacto contacto) {
        return mediosDeContacto.stream()
                .filter(c -> contacto.getTipoContacto().equalsIgnoreCase(c.getTipoContacto()))
                .anyMatch(c -> c.getDireccion().equalsIgnoreCase(contacto.getDireccion()));
    }
}
