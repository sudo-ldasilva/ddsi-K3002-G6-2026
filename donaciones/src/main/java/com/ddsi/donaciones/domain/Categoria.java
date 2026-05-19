package com.ddsi.donaciones.domain;
import java.util.ArrayList;

public class Categoria {
    private String nombre;
    private ArrayList<Subcategoria> subcategorias;

    public Categoria(String nombre, ArrayList<Subcategoria> subcategorias) {
        this.nombre = nombre;
        this.subcategorias = subcategorias;
    }

    public String getNombre() {
        return nombre;
    }
    public ArrayList<Subcategoria> getSubcategorias() {
        return subcategorias;
    }
}
