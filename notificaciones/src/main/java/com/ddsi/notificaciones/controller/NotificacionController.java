package com.ddsi.notificaciones.controller;

import com.ddsi.notificaciones.dto.NotificacionRequestDTO;
import com.ddsi.notificaciones.exception.ContactoInvalidoException;
import com.ddsi.notificaciones.service.NotificacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @PostMapping
    public ResponseEntity<?> enviar(@RequestBody NotificacionRequestDTO request) {
        try {
            notificacionService.enviarNotificacion(request);
            return ResponseEntity.ok().build();
        } catch (ContactoInvalidoException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
