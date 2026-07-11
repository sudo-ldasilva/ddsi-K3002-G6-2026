package com.ddsi.incentivos.domain;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.ddsi.incentivos.domain.dto.DonacionIndependienteDTO;
import com.ddsi.incentivos.domain.dto.DonacionesPorMailDTO;
import com.ddsi.incentivos.services.DonacionesService;

public class GestorIncentivos {
    private static GestorIncentivos gestorIncentivos = null;
    private ArrayList<Donante> donantes;
    private ArrayList<Categoria> categorias;

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

            donante = progresarEnCategoria(donante, donacionPorMail.getDonaciones());
            donantes.add(donante);
        }
    }

    private Donante progresarEnCategoria(Donante donante, ArrayList<DonacionIndependienteDTO> donaciones) {
        donante = progresarEnMision(donante, donaciones);
        try {
            donante.cambiarCategoria(donaciones);
            donante = progresarEnCategoria(donante, donaciones);
        } catch (Exception error) {
            return donante;
        }
        return donante;
    }

    private Donante progresarEnMision(Donante donante, ArrayList<DonacionIndependienteDTO> donaciones) {
        Insignia insignia = donante.getMisionActual() != null ? donante.getMisionActual().misionCumplida(donante, donaciones) : null;
        if(insignia != null) {
            donante.agregarInsignia(insignia);
            donante.siguienteMision();
            donante = progresarEnMision(donante, donaciones);
        }
        return donante;
    }

    public ArrayList<Donante> rankingMensual() {
        LocalDate hoy = LocalDate.now();
        int mesActual = hoy.getMonthValue();
        int anioActual = hoy.getYear();

        return donantes.stream()
                .map(d -> new AbstractMap.SimpleEntry<>(d, contarInsigniasDelMes(d, mesActual, anioActual)))
                .filter(entry -> entry.getValue() > 0)
                .sorted(Comparator.comparingLong((Map.Entry<Donante, Long> e) -> e.getValue()).reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private long contarInsigniasDelMes(Donante donante, int mes, int anio) {
        return donante.getInsignias().stream()
                .filter(i -> i.getFechaCompletada().getMonth() == mes
                        && i.getFechaCompletada().getYear() == anio)
                .count();
    }
}
