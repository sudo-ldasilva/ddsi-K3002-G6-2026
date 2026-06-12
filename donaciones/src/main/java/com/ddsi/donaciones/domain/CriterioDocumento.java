package com.ddsi.donaciones.domain;

public class CriterioDocumento implements CriterioDuplicado {

    @Override
    public boolean sonPosibleDuplicado(Donante existente, Donante candidato) {
        Documento docExistente = existente.getDocumento();
        Documento docCandidato = candidato.getDocumento();

        if (docExistente == null || docCandidato == null) return false;
        if (docExistente.getDocumento() == null || docCandidato.getDocumento() == null) return false;

        return docExistente.getDocumento()
                .trim()
                .equalsIgnoreCase(docCandidato.getDocumento().trim());
    }
}
