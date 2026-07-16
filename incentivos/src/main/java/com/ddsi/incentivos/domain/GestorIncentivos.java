package com.ddsi.incentivos.domain;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.ddsi.incentivos.domain.dto.DonacionIndependienteDTO;
import com.ddsi.incentivos.domain.dto.DonacionesPorMailDTO;
import com.ddsi.incentivos.services.DonacionesService;
import com.ddsi.incentivos.services.N8nService;
import com.ddsi.incentivos.services.NotificacionDispatcherService;

public class GestorIncentivos {
    private static GestorIncentivos gestorIncentivos = null;
    private ArrayList<Donante> donantes;
    private ArrayList<Categoria> categorias;
    private Ranking ranking;

    private GestorIncentivos() {
        this.donantes = new ArrayList<>();
        this.categorias = new ArrayList<>();
    }

    public static GestorIncentivos getInstance() {
        if (gestorIncentivos == null) {
            gestorIncentivos = new GestorIncentivos();
        }
        return gestorIncentivos;
    }

    public void actualizarProgreso() {
        DonacionesService donacionesService = new DonacionesService();
        ArrayList<DonacionesPorMailDTO> donacionesActuales = donacionesService.getDonacionesPorMail();

        for (DonacionesPorMailDTO donacionPorMail : donacionesActuales) {
            String mailDonante = donacionPorMail.getMailDonante();

            Donante donante = null;
            Iterator<Donante> it = donantes.iterator();
            while (it.hasNext()) {
                Donante actual = it.next();
                if (actual.getMail().getDireccion().equalsIgnoreCase(mailDonante)) {
                    donante = actual;
                    it.remove();
                    break;
                }
            }

            if (donante == null) {
                donante = new Donante(new Contacto(mailDonante,"mail"), null, 0, new ArrayList<Insignia>(), false);
            }

            progresarEnCategoria(donante, donacionPorMail.getDonaciones());
            donantes.add(donante);
        }
    }

    private void progresarEnCategoria(Donante donante, ArrayList<DonacionIndependienteDTO> donaciones) {
        progresarEnMision(donante, donaciones);

        try {
            donante.cambiarCategoria(donaciones);
            progresarEnCategoria(donante, donaciones);

            NotificacionDispatcherService notif = new NotificacionDispatcherService();
            notif.notificar(new ArrayList<Contacto>(List.of(donante.getMail())), "Has sido promovido a la categoría " + donante.getCategoriaActual().getNombre());
        } catch (Exception error) {
        }
    }

    private void progresarEnMision(Donante donante, ArrayList<DonacionIndependienteDTO> donaciones) {
        Insignia insignia = donante.getMisionActual() != null ? donante.getMisionActual().misionCumplida(donante, donaciones) : null;
        if (insignia == null) return;

        donante.agregarInsignia(insignia);
        donante.siguienteMision();
        NotificacionDispatcherService notif = new NotificacionDispatcherService();
        notif.notificar(new ArrayList<Contacto>(List.of(donante.getMail())), "Has conseguido la insignia " + insignia.getMision().getNombre());
        N8nService n8n = new N8nService();
        n8n.notificar(
            donante.getMail().getDireccion(),
            donante.getCategoriaActual().getNombre(),
            "Ha conseguido la insignia " + insignia.getMision().getNombre()
        );

        progresarEnMision(donante, donaciones);
    }

    public ArrayList<Donante> rankingMensual() {
        LocalDate hoy = LocalDate.now();
        int mesActual = hoy.getMonthValue();
        int añoActual = hoy.getYear();

        if (mesActual != ranking.getMes() && añoActual != ranking.getAño()) {
            ranking = new Ranking(añoActual, mesActual);
        }

        return ranking.getDonantes();
    }

    public long contarInsigniasDelMes(Donante donante, int mes, int anio) {
        return donante.getInsignias().stream()
                .filter(i -> i.getFechaCompletada().getMonth() == mes
                        && i.getFechaCompletada().getYear() == anio)
                .count();
    }

    public ArrayList<Donante> getDonantes() {
        return donantes;
    }

    public Optional<Donante> getDonante(Contacto mail) {
        return donantes.stream().filter(d -> d.getMail().equals(mail)).findFirst();
    }
}
