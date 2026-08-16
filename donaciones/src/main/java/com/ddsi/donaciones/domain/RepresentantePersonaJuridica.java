package com.ddsi.donaciones.domain;

public class RepresentantePersonaJuridica extends Representante {
    private PersonaJuridica personaJuridica;

    public RepresentantePersonaJuridica(Contacto mail, PersonaJuridica personaJuridica) {
        super(mail);
        this.personaJuridica = personaJuridica;
    }

    public PersonaJuridica getPersonaJuridica() {
        return personaJuridica;
    }
}
