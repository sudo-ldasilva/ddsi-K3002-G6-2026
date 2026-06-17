package com.ddsi.donaciones.domain;

public class CampaniaNecesidadExtraordinaria extends CampaniaNecesidad {
    private String situacionExcepcional;

    public CampaniaNecesidadExtraordinaria(EntidadBeneficiaria entidadBeneficiaria, String descripcion, String estado, Subcategoria subcategoria, String situacionExcepcional) {
        super(entidadBeneficiaria, descripcion, estado, subcategoria);
        this.situacionExcepcional = situacionExcepcional;
    }

    public String getSituacionExcepcional() {
        return situacionExcepcional;
    }

    public void setSituacionExcepcional(String situacionExcepcional) {
        this.situacionExcepcional = situacionExcepcional;
    }
}
