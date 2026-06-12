package com.ddsi.donaciones.domain;

import java.util.List;

public class CriterioTelefono implements CriterioDuplicado {

    private static final int DIGITOS_LOCALES = 8;

    @Override
    public boolean sonPosibleDuplicado(Donante existente, Donante candidato) {
        List<String> telesExistente = extraerTelefonos(existente.getContactos()); //viene del problema de contacto
        List<String> telesCandidato = extraerTelefonos(candidato.getContactos());

        return telesExistente.stream()
                .anyMatch(t -> telesCandidato.stream()
                        .anyMatch(tc -> normalizar(t).equals(normalizar(tc))));
    }

    private List<String> extraerTelefonos(List<com.ddsi.notificaciones.Contacto> contactos) {
        return contactos.stream()
                .filter(c -> c instanceof ContactoTelefono)
                .map(c -> ((ContactoTelefono) c).getNumeroCompleto())
                .toList();
    }

//deja solo los digitos
    private String normalizar(String numero) {
        String soloDigitos = numero.replaceAll("[^0-9]", "");
        if (soloDigitos.length() > DIGITOS_LOCALES) {
            return soloDigitos.substring(soloDigitos.length() - DIGITOS_LOCALES);
        }
        return soloDigitos;
    }
}