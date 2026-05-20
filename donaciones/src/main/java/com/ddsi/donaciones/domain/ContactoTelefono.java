package com.ddsi.donaciones.domain;

public class ContactoTelefono implements Contacto {
    private String numero;
    private String codigoDeArea;
    private boolean esFijo;

    public ContactoTelefono(String codigoDeArea, String num, boolean fijo) {
        this.numero = this.codigoDeArea + numero;
        this.esFijo = fijo;
    }

    public void MandarMensaje(String mensaje) {
        // TODO tercerizado
    }
}
