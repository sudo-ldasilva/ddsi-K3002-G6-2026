package com.ddsi.incentivos.domain.dto;

import com.ddsi.incentivos.domain.Contacto;

import java.util.ArrayList;

public class NotificacionRequestDTO {
    private ArrayList<Contacto> contactos;
    private String mensaje;

    public NotificacionRequestDTO() {
    }

    public NotificacionRequestDTO(ArrayList<Contacto> contactos, String mensaje) {
        this.contactos = contactos;
        this.mensaje = mensaje;
    }

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(ArrayList<Contacto> contactos) {
        this.contactos = contactos;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
