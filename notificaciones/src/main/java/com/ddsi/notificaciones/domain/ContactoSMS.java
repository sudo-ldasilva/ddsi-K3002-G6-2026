package com.ddsi.notificaciones;

public class ContactoSMS extends ContactoTelefono {
    private String usuario;

    public ContactoSMS(String numeroCompleto, String usuario) {
        super(numeroCompleto);
        this.usuario = usuario;
    }

    @Override
    public void enviarMensaje(String mensaje) {
        //System.out.println("Enviando SMS a " + usuario + ": " + mensaje);
        // to do
    }
}
