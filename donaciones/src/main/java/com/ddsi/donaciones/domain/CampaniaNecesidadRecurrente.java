package com.ddsi.donaciones.domain;

import java.util.Date;

public class CampaniaNecesidadRecurrente extends CampaniaNecesidad {
    private Periodo periodo;

    public CampaniaNecesidadRecurrente(EntidadBeneficiaria entidadBeneficiaria, String descripcion, Periodo periodo) {
        super(entidadBeneficiaria, descripcion);
        this.periodo = periodo;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
}
