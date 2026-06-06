package com.ddsi.donaciones.domain;

public class Representante{
    private ContactoMail email;

    public Representante(ContactoMail email) {
        this.email = email;
    }

    public ContactoMail getEmail() {
        return email;
    }
}
