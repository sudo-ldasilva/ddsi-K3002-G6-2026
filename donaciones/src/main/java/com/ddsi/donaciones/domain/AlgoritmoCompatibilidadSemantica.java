package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class AlgoritmoCompatibilidadSemantica implements AlgoritmoSeleccion {
    @Override
    public Ranking generarRanking(DonacionIndependiente donacion) {
        ArrayList<CampaniaNecesidad> campaniasElegidas = GestorEntidadesBeneficiarias.getInstance()
                                                                                     .getEntidadesBeneficiarias()
                                                                                     .stream()
                                                                                     .flatMap(e -> e.getNecesidades().stream())
                                                                                     .filter(n -> n.necesitaEstaSubcategoria(donacion.getSubcategoria()))
                                                                                     .sorted(Comparator.comparing(CampaniaNecesidad::getFechaInicio).reversed())
                                                                                     .limit(10)
                                                                                     .collect(Collectors.toCollection(ArrayList::new));
        return new Ranking("Compatibilidad Semántica", campaniasElegidas);
    }
}
