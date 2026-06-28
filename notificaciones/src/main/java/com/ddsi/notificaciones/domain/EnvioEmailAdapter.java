package com.ddsi.notificaciones.domain;

import java.util.List;

public interface EnvioEmailAdapter {
    public void enviarMensaje(String direccion, String mensaje);
}