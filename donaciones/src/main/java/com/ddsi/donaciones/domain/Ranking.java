package com.ddsi.donaciones.domain;

import java.util.ArrayList;

public class Ranking {

    private String algoritmo;
    private ArrayList<CampaniaNecesidad> necesidades;

    public Ranking(String algoritmo, ArrayList<CampaniaNecesidad> necesidades) {
        this.algoritmo = algoritmo;
        this.necesidades = necesidades;
    }

    public String getAlgoritmo() {return algoritmo;}
    public ArrayList<CampaniaNecesidad> getNecesidades() {return necesidades;}
}
