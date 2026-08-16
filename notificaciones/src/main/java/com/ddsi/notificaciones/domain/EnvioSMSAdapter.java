package com.ddsi.notificaciones.domain;

public interface EnvioSMSAdapter {
    public void enviarSMS(String direccion, String mensaje);
}
