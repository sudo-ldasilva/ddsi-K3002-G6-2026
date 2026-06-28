package com.ddsi.notificaciones.domain;

import java.util.List;

public interface EnvioEmailAdapter {
    public void enviarMail(String direccion, String mensaje);
}