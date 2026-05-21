package com.ddsi.donaciones.domain;

public class CampañaNecesidadRecurrente extends CampañaNecesidad {
    private String periodo;

    public CampañaNecesidadRecurrente(EntidadBeneficiaria entidadBeneficiaria, String descripcion, String estado, Subcategoria subcategoria, String periodo) {
        super(entidadBeneficiaria, descripcion, estado, subcategoria);
        this.periodo = periodo;
    }

    public String getPeriodo() {
        return periodo;
    }
}
