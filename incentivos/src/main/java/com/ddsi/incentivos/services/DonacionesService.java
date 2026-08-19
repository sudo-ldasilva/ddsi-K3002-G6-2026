package com.ddsi.incentivos.services;

import com.ddsi.incentivos.domain.Donante;
import com.ddsi.incentivos.domain.GestorIncentivos;
import com.ddsi.incentivos.domain.dto.DonacionIndependienteDTO;
import com.ddsi.incentivos.domain.Contacto;

import com.ddsi.incentivos.domain.dto.DonacionesPorMailDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.core.ParameterizedTypeReference;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class DonacionesService {

    private final RestClient restClient;
    private static final String URL_BASE = "http://localhost:8080/";

    public DonacionesService() {
        this.restClient = RestClient.builder().build();
    }

    public ArrayList<DonacionesPorMailDTO> getDonacionesPorMail() {
        String endpoint = URL_BASE + "donaciones/independientesPorMail";
        return restClient
                .get()
                .uri(endpoint)
                .retrieve()
                .body(new ParameterizedTypeReference<ArrayList<DonacionesPorMailDTO>>() {});
    }

    public ArrayList<DonacionIndependienteDTO> getDonaciones(String mailDonante) {
        String endpoint = URL_BASE + "donantes/" + mailDonante + "/independientesPorMail";
        return restClient
                .get()
                .uri(endpoint)
                .retrieve()
                .body(new ParameterizedTypeReference<ArrayList<DonacionIndependienteDTO>>() {});
    }

    public ArrayList<Contacto> getMediosDeContacto(String mailDonante) {
        String endpoint = URL_BASE + "donantes/" + mailDonante + "/contactos";
        return restClient
                .get()
                .uri(endpoint)
                .retrieve()
                .body(new ParameterizedTypeReference<ArrayList<Contacto>>() {});
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void registrarDonantes() {
        String endpoint = URL_BASE + "donantes";
        ArrayList<Donante> posiblesNuevosDonantes = restClient.get().uri(endpoint).retrieve().body(new ParameterizedTypeReference<ArrayList<Donante>>() {});
        ArrayList<Donante> donantesActuales = GestorIncentivos.getInstance().getDonantes();
        ArrayList<Donante> donantesARegistrar = posiblesNuevosDonantes.stream().filter(nd -> !donantesActuales.contains(nd)).collect(Collectors.toCollection(ArrayList::new));
        donantesARegistrar.forEach(donante -> {GestorIncentivos.getInstance().agregarDonante(donante);});
    }
}
