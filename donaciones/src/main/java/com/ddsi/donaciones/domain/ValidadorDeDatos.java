package com.ddsi.donaciones.domain;

import java.util.ArrayList;

public class ValidadorDeDatos {

    private final ArrayList<CriterioDuplicado> criterios;

    public ValidadorDeDatos(ArrayList<CriterioDuplicado> criterios) {
        this.criterios = criterios;
    }

    public static ValidadorDeDatos conCriteriosPredeterminados() {
        ArrayList<CriterioDuplicado> criterios = new ArrayList<>();
        criterios.add(new CriterioMail());
        return new ValidadorDeDatos(criterios);
    }

    public boolean existeElDonante(ArrayList<Donante> donantesActuales, Donante candidato) {
        return donantesActuales.stream()
                               .anyMatch(existente -> criterios.stream()
                                                                .anyMatch(criterio -> criterio.sonPosibleDuplicado(existente, candidato)));
    }
}
