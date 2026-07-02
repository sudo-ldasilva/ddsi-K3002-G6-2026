package com.ddsi.notificaciones.domain;

import com.infobip.ApiClient;
import com.infobip.ApiKey;
import com.infobip.BaseUrl;
import com.infobip.api.SmsApi;
import com.infobip.model.SmsDestination;
import com.infobip.model.SmsMessage;
import com.infobip.model.SmsRequest;
import com.infobip.model.SmsTextContent;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfobipSMS implements EnvioSMSAdapter {

    @Value("${infobip.api.key}")
    private String apiKey;

    @Value("${infobip.base.url}")
    private String baseUrl;

    @Value("${infobip.sms.from}")
    private String from;

    private SmsApi smsApi;

    @PostConstruct
    public void init() {
        ApiClient client = ApiClient.forApiKey(ApiKey.from(apiKey))
                .withBaseUrl(BaseUrl.from(baseUrl))
                .build();
        smsApi = new SmsApi(client);
    }

    @Override
    public void enviarSMS(String direccion, String mensaje) {
        SmsMessage message = new SmsMessage()
                .sender(from)
                .addDestinationsItem(new SmsDestination().to(direccion))
                .content(new SmsTextContent().text(mensaje));

        SmsRequest request = new SmsRequest()
                .messages(List.of(message));

        try {
            smsApi.sendSmsMessages(request).execute();
        } catch (com.infobip.ApiException e) {
            throw new RuntimeException("Error enviando SMS con Infobip: " + e.getMessage(), e);
        }
    }
}