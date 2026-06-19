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
import org.springframework.web.bind.annotation.PatchMapping;
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
    public ResponseEntity<ArrayList<Donacion>> getDonantes() {
        return ResponseEntity.status(200).body(GestorDonaciones.getInstance().getDonaciones());
    }

    @PostMapping
    public ResponseEntity<Donacion> donar(@RequestBody Donacion donacion) {
        GestorDonaciones.getInstance().donar(donacion);
        return ResponseEntity.status(201).body(donacion);
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<Donacion> actualizarDonacion(@PathVariable UUID uuid, @RequestBody DonacionDTO dto) {
        Donacion donacion = GestorDonaciones.getInstance().getDonacionByUUID(uuid);
        if (donacion == null) return ResponseEntity.status(404).body(null);

        if (dto.getDeposito() != null) {
            donacion.setDeposito(dto.getDeposito());
        }

        if (dto.getDonante() != null) {
            donacion.setDonante(dto.getDonante());
        }

        if (dto.getDescripcion() != null) {
            donacion.setDescripcion(dto.getDescripcion());
        }

        if (dto.getBienes() != null) {
            donacion.setBienes(dto.getBienes());
        }

        if (dto.yaFueSegmentada()) {
            donacion.marcarSegmentada();
        }

        return ResponseEntity.status(200).body(donacion);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Donacion> eliminarDonacion(@PathVariable UUID uuid) {
        Donacion donacion = GestorDonaciones.getInstance().eliminarDonacionByUUID(uuid);
        return ResponseEntity.status( (donacion != null) ? 200 : 404).body(donacion);
    }

    @GetMapping("/independientes")
    public ResponseEntity<ArrayList<DonacionIndependiente>> getDonacionIndependientes() {
        ArrayList<DonacionIndependiente> donacion = GestorDonaciones.getInstance().getDonacionesIndependientes();
        return ResponseEntity.status(200).body(donacion);
    }

    @GetMapping("/independientesPorMail")
    public ResponseEntity<ArrayList<DonacionesPorMailDTO>> getDonacionIndependientesPorMail() {
        ArrayList<DonacionIndependienteDTO> donaciones = GestorDonaciones.getInstance()
                .getDonacionesIndependientes()
                .stream()
                .map(d -> new DonacionIndependienteDTO(
                        d.getUUID(),
                        d.getSubcategoria().getCategoria().getNombre(),
                        d.getDonacion().getDonante().getMail().getDireccion(),
                        d.getBienes().stream().mapToInt(BienDonado::getCantidad).sum(),
                        d.getEstadoActual(),
                        d.getFecha()
                ))
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
    public ResponseEntity<DonacionIndependiente> getDonacionIndependiente(@PathVariable UUID uuid) {
        DonacionIndependiente donacion = GestorDonaciones.getInstance().getDonacionIndependienteByUUID(uuid);
        return ResponseEntity.status(200).body(donacion);
    }

    @PatchMapping("/independientes/{uuid}/estado")
    public ResponseEntity<DonacionIndependiente> actualizarEstadoDonacionIndependiente(@PathVariable UUID uuid, @RequestBody EstadoDonacion estado) {
        DonacionIndependiente donacion = GestorDonaciones.getInstance().getDonacionIndependienteByUUID(uuid);
        if (estado == null) return ResponseEntity.status(404).body(null);

        donacion.cambiarEstado(estado);

        return ResponseEntity.status(200).body(donacion);
    }

    @PostMapping("independientes/{uuidDonacion}/asignaciones/{uuidNecesidad}")
    public ResponseEntity<String> asignarDonacion(@PathVariable UUID uuidDonacion, @PathVariable UUID uuidNecesidad) throws Exception {
        GestorDonaciones.getInstance().asignarDonacionIndependiente(uuidDonacion, uuidDonacion);
        return ResponseEntity.status(200).body("Donacion asignada correctamente");
    }
}
