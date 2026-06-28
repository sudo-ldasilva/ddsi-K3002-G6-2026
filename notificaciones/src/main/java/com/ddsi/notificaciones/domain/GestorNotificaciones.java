package com.ddsi.notificaciones.domain;

import com.ddsi.notificaciones.dto.ContactoDTO;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorNotificaciones {
    private static GestorNotificaciones gestorNotificaciones = null;
    private final Map<TipoContacto, NotificadorStrategy> strategies;

    private GestorNotificaciones(List<NotificadorStrategy> strategyLista) {
        this.strategies = strategyLista.stream().collect(Collectors.toMap(NotificadorStrategy::getTipo, e -> e));
    }

    public static GestorNotificaciones getInstance() {
        if (gestorNotificaciones == null) {
            List<NotificadorStrategy> strategyLista = List.of(
                    new NotificadorEmail(new EnvioEmailAdapter()),
                    new NotificadorSMS(new EnvioSMSAdapter()),
                    new NotificadorWsp(new EnvioWhatsAppAdapter())
            );
            gestorNotificaciones = new GestorNotificaciones(strategyLista);
        }
        return gestorNotificaciones;
    }

    public Boolean enviarMensaje(ContactoDTO contacto, String mensaje) {
        TipoContacto tipo = TipoContacto.valueOf(contacto.getTipoContacto().toUpperCase());
        NotificadorStrategy strategy = strategies.get(tipo);
        if (strategy == null) return false;
        Notificacion notificacion = crearNotificacion(contacto, mensaje);
        strategy.enviarMensaje(notificacion);
        return true;
    }

    private Notificacion crearNotificacion(ContactoDTO contacto, String mensaje) {
        return new Notificacion(
                contacto.getDireccion(),
                mensaje,
                TipoContacto.valueOf(contacto.getTipoContacto()),
                false
        );
    }
}