package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.List;

public class Rankeador {
    private ArrayList<AlgoritmoSeleccion> algoritmos;

    public Rankeador(ArrayList<AlgoritmoSeleccion> algoritmos) {
        this.algoritmos = new ArrayList<>();
    }

    public List<Ranking> generarRankings(DonacionIndependiente donacionIndependiente) {
        //comparo los rankings
        List<Ranking> rankings = algoritmos.stream().map(a -> a.generarRanking(donacionIndependiente)).toList();
        List<CampaniaNecesidad> campaniasCompartidas = rankings.stream().map(r -> r.getNecesidades())
                .reduce(rankings.getFirst().getNecesidades(), (x, y ) -> x.stream().filter(y::contains).toList());
        if(!campaniasCompartidas.isEmpty()) {
            String algoritmo = "final";
            Ranking ranking = new Ranking(algoritmo, campaniasCompartidas);
            List<Ranking> rankingCompartido = new ArrayList<>();
            rankingCompartido.add(ranking);
            return rankingCompartido;
        }
        return rankings;
    }
}
