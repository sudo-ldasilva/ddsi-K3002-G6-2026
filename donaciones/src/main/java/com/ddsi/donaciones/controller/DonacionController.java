package com.ddsi.donaciones.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ddsi.donaciones.domain.*;
import com.ddsi.donaciones.domain.dto.DonacionIndependienteDTO;
import com.ddsi.donaciones.domain.dto.DonacionesPorMailDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.ddsi.donaciones.domain.dto.DonacionDTO;

@RestController
@RequestMapping("/donaciones")
public class DonacionController {

    @GetMapping
    public ResponseEntity<ArrayList<DonacionDTO>> getDonantes() {
        return ResponseEntity.status(200).body(
            GestorDonaciones.getInstance()
                            .getDonaciones()
                            .stream()
                            .map( d -> d.toDto() )
                            .collect(Collectors.toCollection(ArrayList::new))
        );
    }

    @PostMapping
    public ResponseEntity<DonacionDTO> donar(@RequestBody DonacionDTO dto) {
        try {
            Donacion donacion = new Donacion(dto);
            GestorDonaciones.getInstance().donar(donacion);
            return ResponseEntity.status(201).body(donacion.toDto());
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Donacion> actualizarDonacion(@PathVariable UUID uuid, @RequestBody DonacionDTO dto) {
        Donacion donacion = GestorDonaciones.getInstance().getDonacionByUUID(uuid);
        if (donacion == null) return ResponseEntity.status(404).body(null);

        donacion.setDeposito(dto.getDireccionDeposito());
        donacion.setDonante(GestorDonantes.getInstance().getDonante(dto.getDonante()));
        donacion.setDescripcion(dto.getDescripcion());
        donacion.setBienes(dto.getBienes().stream().map(b -> new BienDonado(b)).collect(Collectors.toCollection(ArrayList::new)));
        donacion.setFueSegmentada(dto.yaFueSegmentada());

        return ResponseEntity.status(200).body(donacion);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Donacion> eliminarDonacion(@PathVariable UUID uuid) {
        Donacion donacion = GestorDonaciones.getInstance().eliminarDonacionByUUID(uuid);
        return ResponseEntity.status( (donacion != null) ? 200 : 404).body(donacion);
    }

    @GetMapping("/independientes")
    public ResponseEntity<ArrayList<DonacionIndependienteDTO>> getDonacionIndependientes() {
        ArrayList<DonacionIndependiente> donacion = GestorDonaciones.getInstance().getDonacionesIndependientes();
        return ResponseEntity.status(200).body(donacion.stream().map(d -> d.toDto()).collect(Collectors.toCollection(ArrayList::new)));
    }

    @GetMapping("/independientesPorMail")
    public ResponseEntity<ArrayList<DonacionesPorMailDTO>> getDonacionIndependientesPorMail() {
        ArrayList<DonacionIndependienteDTO> donaciones = GestorDonaciones.getInstance()
                .getDonacionesIndependientes()
                .stream()
                .map(d -> d.toDto())
                .collect(Collectors.toCollection(ArrayList::new));

        Map<String, ArrayList<DonacionIndependienteDTO>> agrupadas = donaciones.stream()
                .collect(Collectors.groupingBy(
                        DonacionIndependienteDTO::getMailDonante,
                        Collectors.toCollection(ArrayList::new)
                ));

        ArrayList<DonacionesPorMailDTO> resultado = agrupadas.entrySet().stream()
                .map(entry -> new DonacionesPorMailDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toCollection(ArrayList::new));
        return ResponseEntity.status(200).body(resultado);
    }

    @GetMapping("/independientes/{uuid}")
    public ResponseEntity<DonacionIndependienteDTO> getDonacionIndependiente(@PathVariable UUID uuid) {
        DonacionIndependiente di = GestorDonaciones.getInstance().getDonacionIndependienteByUUID(uuid);
        return ResponseEntity.status( (di == null) ? 404 : 200).body(di.toDto());
    }

    @PutMapping("/independientes/{uuid}/comprobante")
    public ResponseEntity<DonacionIndependienteDTO> reemplazarComprobanteDonacion(@PathVariable UUID uuid, @RequestBody ComprobanteRecepcion comprobante) {
        DonacionIndependiente donacion = GestorDonaciones.getInstance().getDonacionIndependienteByUUID(uuid);
        if (donacion == null) return ResponseEntity.status(404).body(null);
        donacion.setComprobante(comprobante);
        return ResponseEntity.status(200).body(donacion.toDto());
    }

    @PostMapping("/independientes/{uuidDonacion}/asignaciones/{uuidNecesidad}")
    public ResponseEntity<String> asignarDonacion(@PathVariable UUID uuidDonacion, @PathVariable UUID uuidNecesidad) throws Exception {
        GestorDonaciones.getInstance().asignarDonacionIndependiente(uuidDonacion, uuidNecesidad);
        return ResponseEntity.status(200).body("Donacion asignada correctamente");
    }
}
