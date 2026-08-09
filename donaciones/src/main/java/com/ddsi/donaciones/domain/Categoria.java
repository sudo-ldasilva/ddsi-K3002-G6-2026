package com.ddsi.donaciones.domain;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.ddsi.donaciones.domain.dto.CategoriaDTO;

public class Categoria {
    private String nombre;
    private ArrayList<Subcategoria> subcategorias;
    private boolean esPerecedero;
    private boolean esUsable;

    public Categoria(CategoriaDTO dto) {
        this(
            dto.getNombre(),
            null,
            dto.getEsPerecedero(),
            dto.getEsUsable()
        );
    }

    public Categoria(String nombre, ArrayList<Subcategoria> subcategorias, boolean esPerecedero, boolean esUsable) {
        this.nombre = nombre;
        this.subcategorias = subcategorias;
        this.esPerecedero = esPerecedero;
        this.esUsable = esUsable;
    }

    public void setNombre(String nombre) {
        // TODO Checkear colisiones
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    public ArrayList<Subcategoria> getSubcategorias() {
        return subcategorias;
    }

    public boolean esPerecedero() {
        return this.esPerecedero;
    }
    public void setPerecedero(boolean esPerecedero) {
        this.esPerecedero = esPerecedero;
    }

    public boolean esUsable() {
        return this.esUsable;
    }
    public void setUsable(boolean esUsable) {
        this.esUsable = esUsable;
    }

    public CategoriaDTO toDto() {
    // public CategoriaDTO(String nombre, ArrayList<SubcategoriaDTO> subcategorias, boolean esPerecedero, boolean esUsable) {
        return new CategoriaDTO(nombre, subcategorias.stream().map(s -> s.toDto()).collect(Collectors.toCollection(ArrayList::new)), esPerecedero, esUsable);
    }

    public void setSubcategorias(ArrayList<Subcategoria> subcategorias) {
        this.subcategorias = subcategorias;
    }
}
