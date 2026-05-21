package com.ddsi.donaciones.domain;

public class CampaniaNecesidadRecurrente extends CampaniaNecesidad {
    private String periodo;

    public CampaniaNecesidadRecurrente(EntidadBeneficiaria entidadBeneficiaria, String descripcion, String estado, Subcategoria subcategoria, String periodo) {
        super(entidadBeneficiaria, descripcion, estado, subcategoria);
        this.periodo = periodo;
    }

    public String getPeriodo() {
        return periodo;
    }
}
