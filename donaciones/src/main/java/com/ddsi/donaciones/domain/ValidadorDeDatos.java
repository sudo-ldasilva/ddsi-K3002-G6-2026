package com.ddsi.donaciones.domain;

import java.util.List;

public class ValidadorDeDatos {

    private final List<CriterioDuplicado> criterios;

    public ValidadorDeDatos(List<CriterioDuplicado> criterios) {
        this.criterios = criterios;
    }

    public static ValidadorDeDatos conCriteriosPredeterminados() {
        return new ValidadorDeDatos(List.of(
                new CriterioDocumento(), //no se si es necesario crear uno nuevo cada vez
                new CriterioMail(),
                new CriterioTelefono()
        ));
    }

    public boolean existeElDonante(List<Donante> donantesActuales, Donante candidato) {
        return donantesActuales.stream()
                .anyMatch(existente -> criterios.stream()
                        .anyMatch(criterio -> criterio.sonPosibleDuplicado(existente, candidato)));
    }
}