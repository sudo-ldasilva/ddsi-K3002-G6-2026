package com.ddsi.donaciones.domain.dto;

import java.util.ArrayList;

public class RankingDTO {
    private String algoritmo;
    private ArrayList<CampaniaNecesidadDTO> necesidades;

    public RankingDTO(String algoritmo, ArrayList<CampaniaNecesidadDTO> necesidades) {
        this.algoritmo = algoritmo;
        this.necesidades = necesidades;
    }

    public String getAlgoritmo() {return algoritmo;}
    public ArrayList<CampaniaNecesidadDTO> getNecesidades() {return necesidades;}
}
