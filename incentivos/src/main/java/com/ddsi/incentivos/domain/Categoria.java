package com.ddsi.incentivos.domain;

import java.util.ArrayList;

public class Categoria {
    private String nombre;
    private ArrayList<Mision> misionesACompletar;
    private Categoria categoriaSiguiente;

    public Categoria(String nombre, ArrayList<Mision> misionesACompletar, Categoria categoriaSiguiente) {
        this.nombre = nombre;
        this.misionesACompletar = misionesACompletar;
        this.categoriaSiguiente = categoriaSiguiente;
    }

    public boolean puedePasarASiguienteCategoria(Donante donante) {
        return misionesACompletar.stream().allMatch( m -> m.misionCumplida(donante) != null);
    }

    public Mision getMision(int indice) {
        return indice < misionesACompletar.size() ? misionesACompletar.get(indice) : null;
    }

    public Categoria getSiguiente() {
        return categoriaSiguiente;
    }
}
