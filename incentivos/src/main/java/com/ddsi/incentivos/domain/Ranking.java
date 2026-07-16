package com.ddsi.incentivos.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Ranking {
    private int año;
    private int mes;
    private ArrayList<Donante> donantes;

    public Ranking(int año, int mes) {
        this.año = año;
        this.mes = mes;

        donantes = GestorIncentivos
                   .getInstance()
                   .getDonantes()
                   .stream()
                   .map(d -> new AbstractMap.SimpleEntry<>(d, GestorIncentivos.getInstance().contarInsigniasDelMes(d, mes, año)))
                   .filter(entry -> entry.getValue() > 0)
                   .sorted(Comparator.comparingLong((Map.Entry<Donante, Long> e) -> e.getValue()).reversed())
                   .map(Map.Entry::getKey)
                   .collect(Collectors.toCollection(ArrayList::new));
    }

    public int getAño() {
        return this.año;
    }

    public int getMes() {
        return this.mes;
    }

    public ArrayList<Donante> getDonantes() {
        return this.donantes;
    }
}
