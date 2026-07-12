package com.ddsi.incentivos.controllers;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.ddsi.incentivos.domain.Contacto;
import com.ddsi.incentivos.domain.Donante;
import com.ddsi.incentivos.domain.Mision;
import com.ddsi.incentivos.domain.Insignia;
import com.ddsi.incentivos.domain.dto.DonanteDTO;
import com.ddsi.incentivos.services.DonacionesService;
import com.ddsi.incentivos.domain.GestorIncentivos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class DonanteController {

    @GetMapping("/rankingDonantes")
    public ResponseEntity<ArrayList<DonanteDTO>> getRanking() {
        // PARA TESTING
        // ArrayList<DonanteDTO> donantes = new ArrayList<>();
        // donantes.add(new DonanteDTO(new Contacto("email@email.com", "Email"), new Categoria("pepe", new ArrayList<Mision>(), null), 5, new ArrayList<Insignia>(), true));
        // donantes.add(new DonanteDTO(new Contacto("email2@email.com", "Email"), new Categoria("pepe", new ArrayList<Mision>(), null), 5, new ArrayList<Insignia>(), true));
        // return ResponseEntity.status(200).body(donantes);

        ArrayList<DonanteDTO> rankingDTO = GestorIncentivos.getInstance()
                                                           .rankingMensual()
                                                           .stream()
                                                           .map(d -> new DonanteDTO(d))
                                                           .collect(Collectors.toCollection(ArrayList::new));

        return ResponseEntity.status(200).body(rankingDTO);
    }

    @GetMapping("/donantes/{mail}/insignias")
    public ResponseEntity<ArrayList<Insignia>> getInsignias(@PathVariable String mail) {
        ArrayList<Insignia> insignias = GestorIncentivos.getInstance().getDonante(new Contacto(mail, "mail")).map(d -> d.getInsignias()).orElse(null);
        return ResponseEntity.status( (insignias == null) ? 404 : 200).body(insignias);
    }

    @GetMapping("/donantes/{mail}/mision")
    public ResponseEntity<Mision> getMisiones(@PathVariable String mail) {
        Mision mision = GestorIncentivos.getInstance().getDonante(new Contacto(mail, "mail")).map(d -> d.getMisionActual()).orElse(null);
        return ResponseEntity.status( (mision == null) ? 404 : 200).body(mision);
    }

    @GetMapping("/donantes/{mail}/metricas")
    public ResponseEntity<Integer> getMetricas(@PathVariable String mail) {
        Donante donante = GestorIncentivos.getInstance().getDonante(new Contacto(mail, "mail")).orElse(null);
        if (donante == null) ResponseEntity.status(404).body(null);

        DonacionesService donService = new DonacionesService();
        return ResponseEntity.status(200).body(donante.getMisionActual().getProgresoActual(donante, donService.getDonaciones(mail)));
    }

}
