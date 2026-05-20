package com.ddsi.donaciones.domain;

public class ContactoTelegram extends ContactoTelefono {
    private String usuario;

    public ContactoTelegram(String codigoDeArea, String num, String user) {
        super(codigoDeArea,num);
        this.usuario= user;
    }
}
