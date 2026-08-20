package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.ddsi.donaciones.domain.dto.RankingDTO;

public class Ranking {

    private String algoritmo;
    private ArrayList<CampaniaNecesidad> campañas;

    public Ranking(String algoritmo, ArrayList<CampaniaNecesidad> campañas) {
        this.algoritmo = algoritmo;
        this.campañas = campañas;
    }

    public String getAlgoritmo() {return algoritmo;}
    public ArrayList<CampaniaNecesidad> getCampañas() {return campañas;}

    public RankingDTO toDto() {
        return new RankingDTO(algoritmo, campañas.stream().map(n -> n.toDTO()).collect(Collectors.toCollection(ArrayList::new)));
    }
}
