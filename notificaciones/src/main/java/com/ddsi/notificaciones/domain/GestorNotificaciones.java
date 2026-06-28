package com.ddsi.notificaciones.domain;
import org.springframework.stereotype.Service;
import com.ddsi.notificaciones.dto.ContactoDTO;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GestorNotificaciones {
    //spring boot considera a los services como singleton
    private final Map<TipoContacto, NotificadorStrategy> strategies;

    private GestorNotificaciones(List<NotificadorStrategy> strategyLista) {
        this.strategies = strategyLista.stream().collect(Collectors.toMap(NotificadorStrategy::getTipo, e -> e));
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