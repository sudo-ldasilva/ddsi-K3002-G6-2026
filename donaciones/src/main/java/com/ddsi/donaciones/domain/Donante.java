package com.ddsi.donaciones.domain;

import java.util.List;
import java.util.LinkedList;

public abstract class Donante {
    private ContactoMail mail;
    private List<Contacto> mediosDeContacto;
    private Documento documento;

    public Donante(ContactoMail mail, Documento documento) {
        this.mail = mail;
        this.mediosDeContacto = new LinkedList<Contacto>();
        this.documento = documento;
    }

    public ContactoMail getMail() {
        return this.mail;
    }

    public List<Contacto> getContactos() {
        return mediosDeContacto;
    }

    public void agregarContacto(Contacto contacto){
        this.mediosDeContacto.add(contacto);
    }

    public void eliminarContacto(Contacto contacto){
        this.mediosDeContacto.remove(contacto);
    }

    public void setMediosDeContacto(List<Contacto> mediosDeContacto) {
        this.mediosDeContacto = mediosDeContacto;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Documento getDocumento(){
        return this.documento;
    }

    public boolean tieneMail(ContactoMail contactoMail) {
        return mediosDeContacto.stream()
                .filter(contacto -> contacto instanceof ContactoMail)
                .map(contacto -> (ContactoMail) contacto)
                .anyMatch(contacto -> contacto == contactoMail);
    }
}
