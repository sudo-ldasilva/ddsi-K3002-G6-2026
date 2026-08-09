package com.ddsi.donaciones.controller;

import com.ddsi.donaciones.domain.*;
import com.ddsi.donaciones.domain.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rankingNecesidades")
public class RankingController {

    @GetMapping("/{uuidDonacionIndependiente}")
    public ResponseEntity<ArrayList<RankingDTO>> getRanking(@PathVariable UUID uuidDonacionIndependiente) {
        ArrayList<AlgoritmoSeleccion> algoritmos = new ArrayList<>();
        algoritmos.add(new AlgoritmoPrioridadSubatendidos());
        DonacionIndependiente dis = GestorDonaciones.getInstance().getDonacionIndependienteByUUID(uuidDonacionIndependiente);
        if (dis == null) return ResponseEntity.status(404).body(null);
        return ResponseEntity.status(200).body(new Rankeador(algoritmos).generarRankings(dis).stream().map(r -> r.toDto()).collect(Collectors.toCollection(ArrayList::new)));
    }

}
