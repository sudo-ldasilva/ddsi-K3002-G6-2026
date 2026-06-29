package com.ddsi.donaciones.domain;

import java.util.List;

public class Ranking {

    private String algoritmo;
    private List<CampaniaNecesidad> necesidades;

    public Ranking(String algoritmo, List<CampaniaNecesidad> necesidades) {
        this.algoritmo = algoritmo;
        this.necesidades = necesidades;
    }

    public String getAlgoritmo() {return algoritmo;}
    public List<CampaniaNecesidad> getNecesidades() {return necesidades;}
}
