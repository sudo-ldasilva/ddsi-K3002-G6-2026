package com.ddsi.notificaciones.domain;

import org.springframework.stereotype.Service;

@Service
public class NotificadorWsp implements NotificadorStrategy {

    private final EnvioWhatsAppAdapter adapter;

    public NotificadorWsp(EnvioWhatsAppAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void enviarMensaje(Notificacion notificacion) {
        adapter.enviarWsp(notificacion.getDirecion(), notificacion.getMensaje());
        notificacion.setEstaCompletada(true);
    }

    @Override
    public TipoContacto getTipo() {
        return TipoContacto.WHATSAPP;
    }
}
