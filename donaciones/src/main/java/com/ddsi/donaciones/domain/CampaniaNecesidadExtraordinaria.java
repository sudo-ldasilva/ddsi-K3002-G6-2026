package com.ddsi.donaciones.domain;

import java.util.ArrayList;

public class CampaniaNecesidadExtraordinaria extends CampaniaNecesidad {
    private String situacionExcepcional;

    public CampaniaNecesidadExtraordinaria(EntidadBeneficiaria entidadBeneficiaria, String descripcion, ArrayList<NecesidadIndividual> necesidades, String situacionExcepcional) {
        super(entidadBeneficiaria, descripcion, necesidades);
        this.situacionExcepcional = situacionExcepcional;
    }

    public String getSituacionExcepcional() {
        return situacionExcepcional;
    }

    public void setSituacionExcepcional(String situacionExcepcional) {
        this.situacionExcepcional = situacionExcepcional;
    }

}
