package com.ddsi.donaciones.controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.ddsi.donaciones.domain.*;
import com.ddsi.donaciones.domain.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @GetMapping
    public ResponseEntity<ArrayList<CategoriaDTO>> getCategorias() {
        return ResponseEntity.status(200).body(GestorCategorias.getInstance().getCategorias().stream().map(c -> c.toDto()).collect(Collectors.toCollection(ArrayList::new)));
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> postCategoria(@RequestBody CategoriaDTO dto) {
        try {
            Categoria c = new Categoria(dto);
            ArrayList<Subcategoria> ss = dto.getSubcategorias().stream().map(s -> new Subcategoria(s, c)).collect(Collectors.toCollection(ArrayList::new));
            c.setSubcategorias(ss);
            GestorCategorias.getInstance().agregarCategoria(c);

            return ResponseEntity.status(201).body(dto);
        } catch (Exception e) {
            return ResponseEntity.status(409).body(null);
        }
    }

    @PutMapping("/{nombre}")
    public ResponseEntity<CategoriaDTO> reemplazarCategoria(@PathVariable String nombre, @RequestBody CategoriaDTO dto) {
        Categoria c = GestorCategorias.getInstance().buscarCategoria(nombre);
        if (c == null) ResponseEntity.status(404).body(null);

        c.setNombre(dto.getNombre());
        c.setSubcategorias(dto.getSubcategorias().stream().map(s -> new Subcategoria(s, c)).collect(Collectors.toCollection(ArrayList::new)));
        c.setPerecedero(dto.getEsPerecedero());
        c.setUsable(dto.getEsUsable());

        return ResponseEntity.status(200).body(c.toDto());
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<CategoriaDTO> eliminarCategoria(@PathVariable String nombre) {
        Categoria c = GestorCategorias.getInstance().eliminarCategoria(nombre);
        if (c == null) ResponseEntity.status(404).body(null);

        return ResponseEntity.status(200).body(c.toDto());
    }
}
