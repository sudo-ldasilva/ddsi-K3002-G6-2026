package com.ddsi.donaciones.domain;

public class ContactoTelefono extends Contacto {
    private String numero;
    private String codigoDeArea;
    private bool esFijo;

    public ContactoTelefono(String codigoDeArea, String num, bool fijo) {
        this.numero= substring(this.codigoDeArea)+numero;
        this.esFijo=fijo;
    }
}
