package com.ddsi.notificaciones.domain;

import java.util.List;

public interface EnvioWhatsAppAdapter {
    public void enviarMensaje(String direccion, String mensaje);
}