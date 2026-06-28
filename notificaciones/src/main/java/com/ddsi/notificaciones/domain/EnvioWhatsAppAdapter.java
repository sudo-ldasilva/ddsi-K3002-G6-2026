package com.ddsi.notificaciones.domain;

import java.util.List;

public interface EnvioWhatsAppAdapter {
    public void enviarWsp(String direccion, String mensaje);
}