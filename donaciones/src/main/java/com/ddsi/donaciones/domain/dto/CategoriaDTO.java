package com.ddsi.donaciones.domain.dto;

import java.util.ArrayList;

public class CategoriaDTO {
    private String nombre;
    private ArrayList<SubcategoriaDTO> subcategorias;
    private boolean esPerecedero;
    private boolean esUsable;

    public CategoriaDTO(String nombre, ArrayList<SubcategoriaDTO> subcategorias, boolean esPerecedero, boolean esUsable) {
        this.nombre = nombre;
        this.subcategorias = subcategorias;
        this.esPerecedero = esPerecedero;
        this.esUsable = esUsable;
    }

    public String getNombre() {
        return nombre;
    }
    public ArrayList<SubcategoriaDTO> getSubcategorias() {
        return subcategorias;
    }

    public boolean getEsPerecedero() {
        return this.esPerecedero;
    }

    public boolean getEsUsable() {
        return this.esUsable;
    }
}
