package com.ddsi.notificaciones.domain;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioWhatsapp implements EnvioWhatsAppAdapter {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.whatsapp.from}")
    private String from;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    @Override
    public void enviarWsp(String direccion, String mensaje) {
        Message.creator(
                new PhoneNumber("whatsapp:" + direccion),
                new PhoneNumber(from),
                mensaje
        ).create();
    }
}