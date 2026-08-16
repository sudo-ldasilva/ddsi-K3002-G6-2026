package com.ddsi.notificaciones.controller;

import com.ddsi.notificaciones.dto.NotificacionRequestDTO;
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

    @PostMapping
    public ResponseEntity<Integer> enviar(@RequestBody NotificacionRequestDTO request) {
        boolean resultado = notificacionService.enviar(request);
        if (resultado) {
            return ResponseEntity.ok(0);
        } else {
            return ResponseEntity.status(207).body(1);
        }
    }
}
