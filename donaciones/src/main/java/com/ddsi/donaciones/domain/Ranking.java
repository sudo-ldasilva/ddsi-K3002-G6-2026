package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.ddsi.donaciones.domain.dto.RankingDTO;

public class Ranking {

    private String algoritmo;
    private ArrayList<CampaniaNecesidad> necesidades;

    public Ranking(String algoritmo, ArrayList<CampaniaNecesidad> necesidades) {
        this.algoritmo = algoritmo;
        this.necesidades = necesidades;
    }

    public String getAlgoritmo() {return algoritmo;}
    public ArrayList<CampaniaNecesidad> getNecesidades() {return necesidades;}

    public RankingDTO toDto() {
        return new RankingDTO(algoritmo, necesidades.stream().map(n -> n.toDTO()).collect(Collectors.toCollection(ArrayList::new)));
    }
}
