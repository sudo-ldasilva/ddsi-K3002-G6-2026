package com.ddsi.donaciones.domain;

import java.util.List;

public class CriterioMail implements CriterioDuplicado {

    @Override
    public boolean sonPosibleDuplicado(Donante existente, Donante candidato) {
        List<String> mailsExistente = extraerMails(existente.getContactos());
        List<String> mailsCandidato = extraerMails(candidato.getContactos());

        return mailsExistente.stream()
                .anyMatch(mail -> mailsCandidato.stream()
                        .anyMatch(m -> m.equalsIgnoreCase(mail)));
    }
    //problema de contactos
    private List<String> extraerMails(List<Contacto> contactos) {
        return contactos.stream()
                .filter(c -> c != null)
                .map(c -> ((Contacto) c).getDireccion().trim().toLowerCase())
                .toList();
    }
}
