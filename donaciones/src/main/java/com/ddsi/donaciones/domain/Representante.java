package com.ddsi.donaciones.domain;

public class Representante{
    private ContactoMail email;
    private PersonaJuridica personaJuridica;
    private EntidadBeneficiaria entidadBeneficiaria;

    public Representante(ContactoMail email, PersonaJuridica personaJuridica, EntidadBeneficiaria entidadBeneficiaria) {
        this.email = email;
        this.personaJuridica = personaJuridica;
        this.entidadBeneficiaria = entidadBeneficiaria;
    }

    public ContactoMail getEmail() {
        return email;
    }

    public PersonaJuridica getPersonaJuridica() {
        return personaJuridica;
    }

    public EntidadBeneficiaria getEntidadBeneficiaria() {
        return entidadBeneficiaria;
    }
}
