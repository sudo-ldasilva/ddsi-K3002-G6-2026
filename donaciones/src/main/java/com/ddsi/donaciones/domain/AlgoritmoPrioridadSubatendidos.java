package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class AlgoritmoPrioridadSubatendidos implements AlgoritmoSeleccion {
    @Override
    public Ranking generarRanking(DonacionIndependiente donacion) {
        ArrayList<CampaniaNecesidad> campaniasElegidas = new ArrayList<>();
        ArrayList<EntidadBeneficiaria> entidades = GestorEntidadesBeneficiarias.getInstance().getEntidadesBeneficiarias();
        entidades.stream().filter(e -> e.necesitaEstaSubcategoria(donacion.getSubcategoria()));
        entidades.sort(Comparator.comparing(EntidadBeneficiaria::getCantidadDeDonacionesDelCuatrimestre));

        for (int i =0; campaniasElegidas.size() < 10 && i < entidades.size();i++) {
            campaniasElegidas.addAll(entidades.get(i).getNecesidadesPorSubcategoria(donacion.getSubcategoria()));
        }

        campaniasElegidas = campaniasElegidas.stream()
                                             .limit(10)
                                             .collect(Collectors.toCollection(ArrayList::new));

        return new Ranking("Prioridad Subatendidos", campaniasElegidas);
    }
}
