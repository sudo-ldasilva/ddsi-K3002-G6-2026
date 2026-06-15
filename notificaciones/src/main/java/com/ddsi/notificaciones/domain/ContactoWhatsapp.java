package com.ddsi.notificaciones;

public class ContactoWhatsapp extends ContactoTelefono {

    public ContactoWhatsapp(String numeroCompleto) {
        super(numeroCompleto);
    }

    @Override
    public void enviarMensaje(String mensaje) {
       // System.out.println("Enviando WHATSAPP: " + mensaje);
        // to do
    }
}
