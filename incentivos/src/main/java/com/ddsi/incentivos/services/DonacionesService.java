package com.ddsi.incentivos.services;

import com.ddsi.incentivos.domain.dto.DonacionIndependienteDTO;
import com.ddsi.incentivos.domain.Contacto;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;

import java.util.ArrayList;

@Service
public class DonacionesService {

    private final RestClient restClient;
    private static final String URL_BASE = "http://localhost:8080/";

    public DonacionesService() {
        this.restClient = RestClient.builder().build();
    }

    public ArrayList<DonacionIndependienteDTO> getDonaciones(String mailDonante) {
        String endpoint = URL_BASE + "donante/" + mailDonante + "/donaciones";
        return restClient
                .post()
                .uri(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mailDonante)
                .retrieve()
                .body(new ParameterizedTypeReference<ArrayList<DonacionIndependienteDTO>>() {});
    }

    public ArrayList<Contacto> getMediosDeContacto(String mailDonante) {
        String endpoint = URL_BASE + "donante/" + mailDonante + "/contactos";
        return restClient
                .post()
                .uri(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mailDonante)
                .retrieve()
                .body(new ParameterizedTypeReference<ArrayList<Contacto>>() {});
    }
}
