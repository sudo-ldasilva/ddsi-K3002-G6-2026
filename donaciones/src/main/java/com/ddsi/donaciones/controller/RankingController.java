package com.ddsi.donaciones.controller;

import com.ddsi.donaciones.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rankingNecesidades")
public class RankingController {

    @GetMapping("/{uuidDonacionIndependiente}")
    public ResponseEntity<List<Ranking>> getRanking(@PathVariable UUID uuidDonacionIndependiente) {
        ArrayList<AlgoritmoSeleccion> algoritmos = new ArrayList<>();
        algoritmos.add(new AlgoritmoPrioridadSubatendidos());
        return ResponseEntity.status(200).body(new Rankeador(algoritmos).generarRankings(GestorDonaciones.getInstance().getDonacionIndependienteByUUID(uuidDonacionIndependiente)));
    }

}
