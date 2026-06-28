package com.ddsi.notificaciones.domain;

import org.springframework.stereotype.Service;

@Service
public class NotificadorEmail implements NotificadorStrategy{
    private final EnvioEmailAdapter adapter;

    public NotificadorEmail(EnvioEmailAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void enviarMensaje(Notificacion notificacion) {
        adapter.enviarMail(notificacion.getDirecion(), notificacion.getMensaje());
        notificacion.setEstaCompletada(true);
    }

    @Override
    public TipoContacto getTipo(){return TipoContacto.EMAIL;}
}
