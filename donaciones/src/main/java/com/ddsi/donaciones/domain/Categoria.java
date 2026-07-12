package com.ddsi.donaciones.domain;
import java.util.ArrayList;

public class Categoria {
    private String nombre;
    private ArrayList<Subcategoria> subcategorias;
    public boolean esPerecedero;
    public boolean esUsable;

    public Categoria(String nombre, ArrayList<Subcategoria> subcategorias, boolean esPerecedero, boolean esUsable) {
        this.nombre = nombre;
        this.subcategorias = subcategorias;
        this.esPerecedero = esPerecedero;
        this.esUsable = esUsable;
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
}
