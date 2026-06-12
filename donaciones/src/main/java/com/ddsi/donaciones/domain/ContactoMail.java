package com.ddsi.donaciones.domain;

import com.ddsi.notificaciones.Contacto;

public class ContactoMail implements Contacto {
    private String direccion;
    public ContactoMail(String dir) {
        this.direccion = dir;
    }

    public void MandarMensaje(String mensaje) {
        // TODO tercerizado
    }

    public String getDireccion() {
        return direccion;
    }
}
