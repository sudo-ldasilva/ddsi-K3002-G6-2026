package com.ddsi.notificaciones.domain;

import java.util.List;

public interface EnvioSMSAdapter {
    public void enviarSMS(String direccion, String mensaje);
}