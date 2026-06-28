package com.ddsi.notificaciones.service;

import com.ddsi.notificaciones.domain.GestorNotificaciones;
import com.ddsi.notificaciones.dto.ContactoDTO;
import com.ddsi.notificaciones.dto.NotificacionRequestDTO;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class NotificacionService {
    private final GestorNotificaciones gestor;

    public NotificacionService(GestorNotificaciones gestor) {
        this.gestor = gestor;
    }

    public boolean enviar(NotificacionRequestDTO request) {
        boolean todoOk = true;
        for (ContactoDTO contacto : request.getContactos()) {
            boolean enviado = gestor.enviarMensaje(contacto, request.getMensaje());
            if (!enviado) todoOk = false;
        }
        return todoOk;
    }

    private boolean validar(NotificacionRequestDTO request) {
        if (request.getContactos() == null) {
            return false;
        }
        for (ContactoDTO contacto : request.getContactos()) {
            if (contacto.getTipoContacto() == null || contacto.getTipoContacto().isBlank()) {
                return false;
            }
            if (contacto.getDireccion() == null || contacto.getDireccion().isBlank()) {
                return false;
            }
        }
        if (request.getMensaje() == null || request.getMensaje().isBlank()) {
            return false;
        }
        return true;
    }
}
