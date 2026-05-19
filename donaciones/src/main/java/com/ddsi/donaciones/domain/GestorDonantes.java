package com.ddsi.donaciones.domain;

public class GestorDonantes {
    public static GestorDonantes gestorDonantes = null;

    private GestorDonantes() {
    }

    public static GestorDonantes getInstance() {
        if (gestorDonantes == null) {
            gestorDonantes = new GestorDonantes();
        }

        return gestorDonantes;
    }
}
