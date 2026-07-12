package com.ddsi.donaciones.domain;

import com.ddsi.donaciones.service.NotificacionDispatcherService;

import java.util.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class GestorDonantes {
    private static GestorDonantes gestorDonantes = null;
    private ArrayList<Donante> donantesRegistrados = null;

    private GestorDonantes() {
        donantesRegistrados = new ArrayList<>();
    }

    public ArrayList<Donante> getDonantes() {
        return this.donantesRegistrados;
    }

    public Donante getDonante(Contacto mail) {
        for (int i = 0; i < donantesRegistrados.size(); i++) {
            if (donantesRegistrados.get(i).getMail().equals(mail)) {
                return donantesRegistrados.get(i);
            }
        }
        return null;
    }

    public Donante eliminarDonante(Contacto mail) {
        for (int i = 0; i < donantesRegistrados.size(); i++) {
            if (donantesRegistrados.get(i).getMail().equals(mail)) {
                return donantesRegistrados.remove(i);
            }
        }
        return null;
    }

    public static GestorDonantes getInstance() {
        if (gestorDonantes == null) {
            gestorDonantes = new GestorDonantes();
        }

        return gestorDonantes;
    }

    public void registrarDonante(Donante donante) {
        this.donantesRegistrados.add(donante);
        try {
            NotificacionDispatcherService notificacionDispatcherService = new NotificacionDispatcherService();
            ArrayList<Contacto> contactosDonante = new ArrayList<>(donante.getContactos());
            contactosDonante.addFirst(donante.getMail());
            notificacionDispatcherService.notificar(contactosDonante, "Bienvenida: Gracias por unirte a la iniciativa");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void registrarDonanteSinNotificar(Donante donante) {
        this.donantesRegistrados.add(donante);
    }

    public void notificarDonantesInactivos() {
        donantesRegistrados.stream().filter(d -> {
            return GestorDonaciones.getInstance()
                .getDonacionesByDonante(d)
                .stream()
                .max(Comparator.comparing(Donacion::getFecha))
                .map(donacion -> {
                    return TimeUnit.DAYS.convert(new Date().getTime() - donacion.getFecha().getTime(),TimeUnit.MILLISECONDS) == 20;
                })
                .orElse(false); // si no tiene donaciones
        })
        .forEach( d -> {
            NotificacionDispatcherService notif = new NotificacionDispatcherService();
            notif.notificar(d.getContactos(), "No has donado hace tiempo! Considera realiza una donación :)");
        });
    }
}
