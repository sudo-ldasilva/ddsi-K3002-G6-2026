package com.ddsi.notificaciones.controller;

import com.ddsi.notificaciones.domain.GestorNotificaciones;
import com.ddsi.notificaciones.dto.NotificacionRequestDTO;
import com.ddsi.notificaciones.dto.ContactoDTO;
import com.ddsi.notificaciones.service.NotificacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {
    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @PostMapping("/enviar")
    public ResponseEntity<String> enviar(@RequestBody NotificacionRequestDTO request) {
        boolean resultado = notificacionService.enviar(request);
        if (resultado) {
            return ResponseEntity.ok("Notificaciones enviadas correctamente");
        } else {
            return ResponseEntity.status(207).body("Algunas notificaciones fallaron");
        }
    }
}
