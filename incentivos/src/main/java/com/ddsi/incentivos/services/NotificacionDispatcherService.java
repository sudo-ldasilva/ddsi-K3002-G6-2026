package com.ddsi.donaciones.service;

import com.ddsi.donaciones.domain.Contacto;
import com.ddsi.donaciones.domain.dto.NotificacionRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.http.MediaType;

import java.util.ArrayList;

@Service
public class NotificacionDispatcherService {

    private final RestClient restClient;
    private static final String URL_NOTIFICACIONES = "http://localhost:8081/notificaciones/enviar";

    public NotificacionDispatcherService() {
        this.restClient = RestClient.builder().build();
    }

    public void notificar(ArrayList<Contacto> contactos, String mensaje) {
        NotificacionRequestDTO request = new NotificacionRequestDTO(
                contactos,
                mensaje
        );

        restClient
                .post()
                .uri(URL_NOTIFICACIONES)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toBodilessEntity();
    }
}