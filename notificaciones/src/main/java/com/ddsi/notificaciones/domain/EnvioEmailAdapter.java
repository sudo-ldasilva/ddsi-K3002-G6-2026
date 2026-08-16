package com.ddsi.notificaciones.domain;

public interface EnvioEmailAdapter {
    public void enviarMail(String direccion, String mensaje);
}
