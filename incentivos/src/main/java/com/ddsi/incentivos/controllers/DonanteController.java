package com.ddsi.incentivos.controllers;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.ddsi.incentivos.domain.Donante;
import com.ddsi.incentivos.domain.Mision;
import com.ddsi.incentivos.domain.Insignia;
import com.ddsi.incentivos.domain.dto.DonanteDTO;
import com.ddsi.incentivos.services.DonacionesService;
import com.ddsi.incentivos.domain.GestorIncentivos;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class DonanteController {

    @GetMapping("/rankingDonantes")
    public ResponseEntity<ArrayList<DonanteDTO>> getRanking() {
        ArrayList<DonanteDTO> rankingDTO = GestorIncentivos.getInstance()
                                                           .rankingMensual()
                                                           .stream()
                                                           .map(d -> new DonanteDTO(d))
                                                           .collect(Collectors.toCollection(ArrayList::new));

        return ResponseEntity.status(200).body(rankingDTO);
    }

    @GetMapping("/donantes/{mail}/insignias")
    public ResponseEntity<ArrayList<Insignia>> getInsignias(@PathVariable String mail) {
        Optional<Donante> donanteQuizas = GestorIncentivos.getInstance().getDonante(mail);
        if (donanteQuizas.isEmpty()) return ResponseEntity.status(404).body(null);

        Donante donante = donanteQuizas.get();
        return ResponseEntity.status(200).body(donante.getInsignias());
    }

    @GetMapping("/donantes/{mail}/mision")
    public ResponseEntity<Mision> getMisiones(@PathVariable String mail) {
        Optional<Donante> donanteQuizas = GestorIncentivos.getInstance().getDonante(mail);
        if (donanteQuizas.isEmpty()) return ResponseEntity.status(404).body(null);

        Donante donante = donanteQuizas.get();
        return ResponseEntity.status(200).body(donante.getMisionActual());
    }

    @GetMapping("/donantes/{mail}/metricas")
    public ResponseEntity<Integer> getMetricas(@PathVariable String mail) {
        Donante donante = GestorIncentivos.getInstance().getDonante(mail).orElse(null);
        if (donante == null) ResponseEntity.status(404).body(null);

        DonacionesService donService = new DonacionesService();
        return ResponseEntity.status(200).body(donante.getMisionActual().getProgresoActual(donante, donService.getDonaciones(mail)));
    }
}
