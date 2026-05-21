package com.ddsi.donaciones.domain;

import java.util.ArrayList;

public class DonacionIndependiente {
    private Subcategoria subcategoria;
    private NecesidadIndividual necesidad;
    private Donacion donacion;
    private ArrayList<BienDonado> bienes;

    public DonacionIndependiente(Subcategoria subcategoria, NecesidadIndividual necesidad, Donacion donacion) {
        this.subcategoria = subcategoria;
        this.necesidad = necesidad;
        this.donacion = donacion;
        this.bienes = new ArrayList<>();
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public NecesidadIndividual getNecesidad() {
        return necesidad;
    }

    public Donacion getDonacion() {
        return donacion;
    }

    public ArrayList<BienDonado> getBienes() {
        return bienes;
    }

    public void agregarBien(BienDonado bien) {
        bienes.add(bien);
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public void setNecesidad(NecesidadIndividual necesidad) {
        this.necesidad = necesidad;
    }
}
