package com.ddsi.donaciones.domain;

import com.ddsi.donaciones.domain.GestorEntidadesBeneficiarias;

import java.util.ArrayList;
import java.util.Comparator;

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
        return new Ranking("Prioridad Subatendidos", campaniasElegidas);
    }
}
