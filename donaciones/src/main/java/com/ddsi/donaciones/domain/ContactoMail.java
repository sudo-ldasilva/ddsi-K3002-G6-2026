package com.ddsi.donaciones.domain;

public class ContactoMail extends Contacto {
    private String direccion;
    public ContactoMail(String dir){this.direccion = "mailto:"+dir;};
}
