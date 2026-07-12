package com.ddsi.incentivos.controllers;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.ddsi.incentivos.domain.dto.DonanteDTO;
import com.ddsi.incentivos.domain.GestorIncentivos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rankingDonantes")
public class DonanteController {

    @GetMapping
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

}
