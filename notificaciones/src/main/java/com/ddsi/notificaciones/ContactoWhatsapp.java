package com.ddsi.notificaciones;

public class ContactoWhatsapp extends ContactoTelefono {
    public ContactoWhatsapp(String codigoDeArea, String num) {
        super(codigoDeArea);
    };

    public void MandarMensaje(String mensaje) {
        // TODO tercerizado
    }
}
