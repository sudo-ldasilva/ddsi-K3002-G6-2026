package com.ddsi.notificaciones.domain;

import org.springframework.stereotype.Service;

@Service
public class NotificadorSMS implements NotificadorStrategy {
    private final EnvioSMSAdapter adapter;

    public NotificadorSMS(EnvioSMSAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void enviarMensaje(Notificacion notificacion) {
        adapter.enviarSMS(notificacion.getDirecion(), notificacion.getMensaje());
        notificacion.setEstaCompletada(true);
    }

    @Override
    public TipoContacto getTipo() {
        return TipoContacto.SMS;
    }
}