package com.ddsi.donaciones.domain;

public class CampañaNecesidadExtraordinaria {
    private String situacionExcepcional;

    public CampañaNecesidadExtraordinaria(String situacionExcepcional) {
        this.situacionExcepcional = situacionExcepcional;
    }

    public String getSituacionExcepcional() {
        return situacionExcepcional;
    }
}
