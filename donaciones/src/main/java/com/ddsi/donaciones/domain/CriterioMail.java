package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CriterioMail implements CriterioDuplicado {

    @Override
    public boolean sonPosibleDuplicado(Donante existente, Donante candidato) {
        ArrayList<String> mailsExistente = extraerMails(existente.getMediosDeContacto());
        ArrayList<String> mailsCandidato = extraerMails(candidato.getMediosDeContacto());

        return mailsExistente.stream()
                .anyMatch(mail -> mailsCandidato.stream()
                        .anyMatch(m -> m.equalsIgnoreCase(mail)));
    }

    private ArrayList<String> extraerMails(ArrayList<Contacto> contactos) {
        return contactos.stream()
                .filter(c -> "mail".equalsIgnoreCase(c.getTipoContacto()))
                .map(c -> c.getDireccion().trim().toLowerCase())
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
