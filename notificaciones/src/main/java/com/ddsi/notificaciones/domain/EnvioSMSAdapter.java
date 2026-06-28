package com.ddsi.notificaciones.domain;

import java.util.List;

public interface EnvioSMSAdapter {
    public void enviarMensaje(String direccion, String mensaje);
}