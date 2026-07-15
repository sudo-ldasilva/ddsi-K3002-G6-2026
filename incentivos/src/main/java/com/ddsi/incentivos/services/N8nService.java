package com.ddsi.incentivos.services;

import com.ddsi.incentivos.domain.dto.N8nDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.http.MediaType;

@Service
public class N8nService {

    private final RestClient restClient;
    private static final String URL_N8N = "http://localhost:5678/webhook-test/10973e82-bed5-4563-9f2f-b2b37f836b00";

    public N8nService() {
        this.restClient = RestClient.builder().build();
    }

    public void notificar(String nombreUsuario, String nombreInsignia, String descripcion) {
        N8nDto request = new N8nDto(nombreUsuario, nombreInsignia, descripcion);

        restClient
                .post()
                .uri(URL_N8N)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toBodilessEntity();
    }
}
