package com.ddsi.donaciones.domain;

public class CampaniaNecesidadRecurrente extends CampaniaNecesidad {
    private String periodo;

    public CampaniaNecesidadRecurrente(EntidadBeneficiaria entidadBeneficiaria, String descripcion, String estado, String periodo) {
        super(entidadBeneficiaria, descripcion, estado);
        this.periodo = periodo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public boolean esRecurrente(){
        return true;
    }

    public boolean esExtraordinaria(){
        return false;
    }
}
