package com.ddsi.donaciones.domain;

public class RepresentanteEntidadBeneficiaria extends Representante{
    private EntidadBeneficiaria entidadBeneficiaria;

    public RepresentanteEntidadBeneficiaria(Contacto email, EntidadBeneficiaria entidadBeneficiaria) {
        super(email);
        this.entidadBeneficiaria = entidadBeneficiaria;
    }

    public EntidadBeneficiaria getEntidadBeneficiaria() {
        return entidadBeneficiaria;
    }

    public void setEntidadBeneficiaria(EntidadBeneficiaria entidadBeneficiaria) {
        this.entidadBeneficiaria = entidadBeneficiaria;
    }
}
