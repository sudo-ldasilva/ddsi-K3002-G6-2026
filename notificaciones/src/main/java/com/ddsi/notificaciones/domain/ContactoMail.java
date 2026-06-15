package com.ddsi.donaciones.domain;

import com.ddsi.notificaciones.Contacto;

public class ContactoMail implements Contacto {
    private String direccion;

    public ContactoMail(String dir) {
        this.direccion = dir;
    }

    @Override
    public void enviarMensaje(String mensaje) {
        // System.out.println("Enviando MAIL a " + direccion + ": " + mensaje);
        // to do ..
    }

    public String getDireccion() {
        return direccion;
    }
}
