package com.ddsi.notificaciones;

public class ContactoTelegram extends ContactoTelefono {
    private String usuario;

    public ContactoTelegram(String numeroCompleto, String usuario) {
        super(numeroCompleto);
        this.usuario = usuario;
    }

    @Override
    public void enviarMensaje(String mensaje) {
        //System.out.println("Enviando TELEGRAM a " + usuario + ": " + mensaje);
        // to do
    }
}
