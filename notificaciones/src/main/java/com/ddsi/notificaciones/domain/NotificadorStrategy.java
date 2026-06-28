package com.ddsi.notificaciones.domain;

import com.ddsi.notificaciones.notificacion;
public interface NotificadorStrategy {
    public void enviarMensaje(Notificacion notificacion);
    public void getTipo();
}