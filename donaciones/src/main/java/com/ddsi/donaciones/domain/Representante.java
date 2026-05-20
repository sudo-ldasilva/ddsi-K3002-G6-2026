package com.ddsi.donaciones.domain;

public class Representante{
    private String email;
    private PersonaJuridica personaJuridica;
    private EntidadBeneficiaria entidadBeneficiaria;

    public Representante(String email, PersonaJuridica personaJuridica, EntidadBeneficiaria entidadBeneficiaria) {
        this.email = email;
        this.personaJuridica = personaJuridica;
        this.entidadBeneficiaria = entidadBeneficiaria;
    }

    public String getEmail() {
        return email;
    }

    public PersonaJuridica getPersonaJuridica() {
        return personaJuridica;
    }

    public EntidadBeneficiaria getEntidadBeneficiaria() {
        return entidadBeneficiaria;
    }
}
