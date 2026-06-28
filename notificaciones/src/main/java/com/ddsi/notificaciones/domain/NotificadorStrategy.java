package com.ddsi.notificaciones.domain;

public interface NotificadorStrategy {
    public void enviarMensaje(Notificacion notificacion);
    public TipoContacto getTipo();
}