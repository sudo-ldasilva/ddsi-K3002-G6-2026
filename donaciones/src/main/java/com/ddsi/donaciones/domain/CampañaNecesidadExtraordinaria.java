package com.ddsi.donaciones.domain;

public class CampañaNecesidadExtraordinaria extends CampañaNecesidad {
    private String situacionExcepcional;

    public CampañaNecesidadExtraordinaria(EntidadBeneficiaria entidadBeneficiaria, String descripcion, String estado, Subcategoria subcategoria, String situacionExcepcional) {
        super(entidadBeneficiaria, descripcion, estado, subcategoria);
        this.situacionExcepcional = situacionExcepcional;
    }

    public String getSituacionExcepcional() {
        return situacionExcepcional;
    }
}
