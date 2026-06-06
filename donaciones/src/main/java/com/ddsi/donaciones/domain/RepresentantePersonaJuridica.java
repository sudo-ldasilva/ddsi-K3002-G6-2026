package com.ddsi.donaciones.domain;

public class RepresentantePersonaJuridica extends Representante {
    private PersonaJuridica personaJuridica;

    public RepresentantePersonaJuridica(ContactoMail email, PersonaJuridica personaJuridica) {
        super(email);
        this.personaJuridica = personaJuridica;
    }

    public PersonaJuridica getPersonaJuridica() {
        return personaJuridica;
    }
}
