package com.ddsi.donaciones.domain;

import com.ddsi.donaciones.service.NotificacionDispatcherService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
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

    public void agregarDonante(Donante d) {
        donantesRegistrados.add(d);
    }

    public void dropDonantes() { donantesRegistrados = new ArrayList<>(); }

    public Donante getDonante(String mail) {
        for (int i = 0; i < donantesRegistrados.size(); i++) {
            if (donantesRegistrados.get(i).getMail().equals(mail)) {
                return donantesRegistrados.get(i);
            }
        }
        return null;
    }

    public void setDonantes(ArrayList<Donante> ds) {
        donantesRegistrados = ds;
    }

    public Donante eliminarDonante(String mail) {
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
        // try {
        //     NotificacionDispatcherService notificacionDispatcherService = new NotificacionDispatcherService();
        //     ArrayList<Contacto> contactosDonante = new ArrayList<>(donante.getContactos());
        //     contactosDonante.addFirst(donante.getMail());
        //     notificacionDispatcherService.notificar(contactosDonante, "Bienvenida: Gracias por unirte a la iniciativa");
        // } catch (Exception e) {
        //     throw new RuntimeException(e);
        // }
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
                    return ChronoUnit.DAYS.between(donacion.getFecha(), LocalDate.now()) >= 20;                })
                .orElse(false); // si no tiene donaciones
        })
        .forEach( d -> {
            NotificacionDispatcherService notif = new NotificacionDispatcherService();
            notif.notificar(d.getMediosDeContacto(), "No has donado hace tiempo! Considera realiza una donación :)");
        });
    }
}
