package com.ddsi.logistica.domain;

import java.util.ArrayList;

public class Ruta {
    private ArrayList<Entrega> entregas;
    private Camion camion;

    public Ruta(ArrayList<Entrega> entregas, Camion camion) {
        this.entregas = entregas;
        this.camion = camion;
    }

    public ArrayList<Entrega> getEntregas() {
        return entregas;
    }

    public Camion getCamion() {
        return camion;
    }
}
