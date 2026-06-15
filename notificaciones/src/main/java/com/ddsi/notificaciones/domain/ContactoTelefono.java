package com.ddsi.notificaciones;

public class ContactoTelefono implements Contacto {
    private String numero;
    private String codigoDeArea;
    private boolean esFijo;

    public ContactoTelefono(String numeroCompleto) {
        String[] partesDelNumero = numeroCompleto.split(" ", 2);
        this.codigoDeArea = partesDelNumero[0];
        this.numero = partesDelNumero[1];
        this.esFijo = !this.numero.startsWith("11");
    }

    @Override
    public void enviarMensaje(String mensaje) {
       // System.out.println("Enviando SMS a " + codigoDeArea + " " + numero + ": " + mensaje);// to do
    }
}

