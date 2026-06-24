package com.ddsi.donaciones.domain;

public class CampaniaNecesidadRecurrente extends CampaniaNecesidad {
    private String periodo;

    public CampaniaNecesidadRecurrente(EntidadBeneficiaria entidadBeneficiaria, String descripcion, String periodo) {
        super(entidadBeneficiaria, descripcion);
        this.periodo = periodo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
