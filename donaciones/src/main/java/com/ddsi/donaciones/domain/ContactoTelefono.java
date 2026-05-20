package com.ddsi.donaciones.domain;

public class ContactoTelefono extends Contacto {
    private String numero;
    private String codigoDeArea;
    private boolean esFijo;

    public ContactoTelefono(String codigoDeArea, String num, boolean fijo) {
        this.numero= substring(this.codigoDeArea)+numero;
        this.esFijo=fijo;
    }
}
