package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Rankeador {
    private ArrayList<AlgoritmoSeleccion> algoritmos;

    public Rankeador(ArrayList<AlgoritmoSeleccion> algoritmos) {
        this.algoritmos = algoritmos;
    }

    public ArrayList<Ranking> generarRankings(DonacionIndependiente donacionIndependiente) {
        //comparo los rankings
        ArrayList<Ranking> rankings = algoritmos.stream()
                                                .map(a -> a.generarRanking(donacionIndependiente))
                                                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<CampaniaNecesidad> campaniasCompartidas = new ArrayList<CampaniaNecesidad>(
            rankings.stream()
                    .map(r -> r.getCampañas())
                    .reduce(
                        rankings.getFirst().getCampañas(),
                        (x, y ) -> x.stream()
                                    .filter(y::contains)
                                    .collect(Collectors.toCollection(ArrayList::new))
                    )
        );

        for (CampaniaNecesidad campaña : campaniasCompartidas) {
            System.out.println("camp: " + campaña.getEntidadBeneficiaria().getRazonSocial());
        }
        if(!campaniasCompartidas.isEmpty()) {
            String algoritmo = "final";
            Ranking ranking = new Ranking(algoritmo, campaniasCompartidas);
            ArrayList<Ranking> rankingCompartido = new ArrayList<>();
            rankingCompartido.add(ranking);
            return rankingCompartido;
        }
        return rankings;
    }
}
