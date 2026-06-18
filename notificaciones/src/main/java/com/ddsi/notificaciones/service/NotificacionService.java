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

    private boolean validar(NotificacionRequestDTO request) {
        if (request.getDireccionContacto() == null || request.getDireccionContacto().isBlank()) {
            return false;
        }
        if (request.getTipoContacto() == null || request.getTipoContacto().isBlank()) {
            return false;
        }
        if (request.getMensaje() == null || request.getMensaje().isBlank()) {
            return false;
        }
    }
}
