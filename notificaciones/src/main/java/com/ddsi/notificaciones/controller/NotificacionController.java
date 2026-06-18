package com.ddsi.notificaciones.controller;

import com.ddsi.donaciones.domain.Contacto;
import com.ddsi.notificaciones.domain.GestorNotificaciones;
import com.ddsi.notificaciones.dto.NotificacionRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @PostMapping
    public ResponseEntity<?> enviar(@RequestBody NotificacionRequestDTO request) {
        for (Contacto contacto : request.getContactos()) {
            if (GestorNotificaciones.getInstance().enviarMensaje(contacto, request.getMensaje())) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(404).body("No se encontró un contacto válido para enviar la notificación");
    }
}
