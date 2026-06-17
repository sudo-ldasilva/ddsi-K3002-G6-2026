package com.ddsi.donaciones.service;

import com.ddsi.donaciones.domain.Donante;
import com.ddsi.donaciones.domain.EntidadBeneficiaria;
import com.ddsi.donaciones.domain.dto.DestinatarioNotificacionDTO;
import com.ddsi.donaciones.domain.dto.DestinatarioNotificacionMapper;
import com.ddsi.donaciones.domain.dto.NotificacionRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.http.MediaType;

@Service
public class NotificacionDispatcherService {

    private final RestClient restClient;
    private static final String URL_NOTIFICACIONES = "http://notificaciones/notificaciones";

    public NotificacionDispatcherService() {
        this.restClient = RestClient.builder().build();
    }

    public void notificarDonante(Donante donante, String mensaje) {
        DestinatarioNotificacionDTO destino = DestinatarioNotificacionMapper.desdeDonante(donante);
        enviar(destino, mensaje);
    }

    public void notificarEntidadBeneficiaria(EntidadBeneficiaria entidad, String mensaje) {
        DestinatarioNotificacionDTO destino = DestinatarioNotificacionMapper.desdeEntidadBeneficiaria(entidad);
        enviar(destino, mensaje);
    }

    private void enviar(DestinatarioNotificacionDTO destino, String mensaje) {
        NotificacionRequestDTO request = new NotificacionRequestDTO(
                destino.getTipoContacto(),
                destino.getDireccionContacto(),
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
