package com.ddsi.notificaciones.domain;

import org.springframework.stereotype.Service;

@Service
public class InfobipSMS implements EnvioSMSAdapter {

    @Override
    public void enviarSMS(String direccion, String mensaje) {
        // to do (implementar twilio)
    }
}
