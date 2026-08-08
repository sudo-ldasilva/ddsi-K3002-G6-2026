package com.ddsi.donaciones.controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.ddsi.donaciones.domain.*;
import com.ddsi.donaciones.domain.dto.DonacionIndependienteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DebuggingController {

    @PostMapping("/donaciones/segmentar")
    public ResponseEntity<ArrayList<DonacionIndependienteDTO>> segmentate() {
        ArrayList<DonacionIndependiente> dis = GestorDonaciones.getInstance().segmentarDonaciones();
        return ResponseEntity.status(200).body(
            dis.stream()
               .map(d -> d.toDto())
               .collect(Collectors.toCollection(ArrayList::new))
        );
    }

}
