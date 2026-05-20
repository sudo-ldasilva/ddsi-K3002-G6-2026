package com.ddsi.donaciones.domain;

public class ContactoMail extends Contacto {
    private String direccion;
    public ContactoMail(String dir) {
        this.direccion = dir;
    }

    public void MandarMensaje(String mensaje) {
        // TODO tercerizado
    }
}
