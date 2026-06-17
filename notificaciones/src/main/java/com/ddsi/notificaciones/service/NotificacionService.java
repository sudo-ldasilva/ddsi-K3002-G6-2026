package com.ddsi.notificaciones.service;

import com.ddsi.notificaciones.domain.Contacto;
import com.ddsi.notificaciones.domain.ContactoMail;
import com.ddsi.notificaciones.domain.ContactoTelefono;
import com.ddsi.notificaciones.domain.ContactoTelegram;
import com.ddsi.notificaciones.domain.ContactoWhatsapp;
import com.ddsi.notificaciones.dto.NotificacionRequestDTO;
import com.ddsi.notificaciones.exception.ContactoInvalidoException;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {
    public void enviarNotificacion(NotificacionRequestDTO request) {
        validar(request);

        Contacto contacto = crearContacto(request.getTipoContacto(), request.getDireccionContacto());
        contacto.enviarMensaje(request.getMensaje());
    }

    private void validar(NotificacionRequestDTO request) {
        if (request.getDireccionContacto() == null || request.getDireccionContacto().isBlank()) {
            throw new ContactoInvalidoException("No se recibio un contacto valido para enviar la notificacion");
        }
        if (request.getTipoContacto() == null || request.getTipoContacto().isBlank()) {
            throw new ContactoInvalidoException("No se especifico el tipo de contacto");
        }
        if (request.getMensaje() == null || request.getMensaje().isBlank()) {
            throw new ContactoInvalidoException("El mensaje no puede estar vacio");
        }
    }

    private Contacto crearContacto(String tipoContacto, String direccion) {
        return switch (tipoContacto.toUpperCase()) {
            case "MAIL" -> new ContactoMail(direccion);
            case "TELEFONO", "SMS" -> new ContactoTelefono(direccion);
            case "WHATSAPP" -> new ContactoWhatsapp(direccion);
            case "TELEGRAM" -> new ContactoTelegram(direccion, direccion);
            default -> throw new ContactoInvalidoException("Tipo de contacto no soportado: " + tipoContacto);
        };
    }
}
