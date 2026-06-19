package com.ddsi.donaciones.domain;

import java.util.List;
import java.util.ArrayList;

public abstract class Donante {
    private Contacto mail;
    private ArrayList<Contacto> mediosDeContacto;
    private Documento documento;

    public Donante(Contacto mail, Documento documento) {
        this.mail = mail;
        this.mediosDeContacto = new ArrayList<Contacto>();
        this.documento = documento;
    }

    public Contacto getMail() {
        return this.mail;
    }

    public ArrayList<Contacto> getContactos() {
        return mediosDeContacto;
    }

    public void agregarContacto(Contacto contacto){
        this.mediosDeContacto.add(contacto);
    }

    public void eliminarContacto(Contacto contacto){
        this.mediosDeContacto.remove(contacto);
    }

    public void setMediosDeContacto(ArrayList<Contacto> mediosDeContacto) {
        this.mediosDeContacto = mediosDeContacto;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Documento getDocumento(){
        return this.documento;
    }

    public boolean tieneMail(Contacto contacto){
        return mediosDeContacto.stream().anyMatch((e -> e.equals(contacto)));
    }
}

