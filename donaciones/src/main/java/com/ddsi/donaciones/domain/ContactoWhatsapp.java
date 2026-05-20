package com.ddsi.donaciones.domain;

public class ContactoWhatsapp extends ContactoTelefono {
    public ContactoWhatsapp(String codigoDeArea, String num) {
        super(codigoDeArea, num, false);
    };

    public void MandarMensaje(String mensaje) {
        // TODO tercerizado
    }
}
