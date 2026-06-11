package com.ddsi.notificaciones;

public class ContactoMail implements Contacto {
    private String direccion;
    public ContactoMail(String dir) {
        this.direccion = dir;
    }

    public void MandarMensaje(String mensaje) {
        // TODO tercerizado
    }
}
