package com.ddsi.notificaciones.domain;

import org.springframework.stereotype.Service;

@Service
public class TwilioWhatsapp implements EnvioWhatsAppAdapter {

    @Override
    public void enviarWsp(String direccion, String mensaje) {
        // to do.. (implementar whappi)
    }
}
