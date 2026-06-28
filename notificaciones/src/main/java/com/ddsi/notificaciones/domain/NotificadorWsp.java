package com.ddsi.notificaciones.domain;

@Service
public class NotificadorWsp implements NotificadorStrategy {
    private EnvioWhatsAppAdapter adapter;

    @Override
    public void enviarMensaje(Notificacion notificacion) {
        adapter.enviarNotificacion(notificacion);
    }
}
