package com.ddsi.notificaciones;

public class ContactoTelegram extends ContactoTelefono {
    private String usuario;

    public ContactoTelegram(String codigoDeArea, String num, String user) {
        super(codigoDeArea);
        this.usuario= user;
    }

    public void MandarMensaje(String mensaje) {
        // TODO tercerizado
    }
}
