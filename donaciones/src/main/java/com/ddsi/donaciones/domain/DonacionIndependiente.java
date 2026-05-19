package com.ddsi.donaciones.domain;

public class DonacionIndependiente {
    private Subcategoria subcategoria;
    private necesidadIndividual necesidad;
    private Donacion donacion;
    private BienDonado bien;

    public DonacionIndependiente(Subcategoria subcategoria, necesidadIndividual necesidad, Donacion donacion, BienDonado bien) {
        this.subcategoria = subcategoria;
        this.necesidad = necesidad;
        this.donacion = donacion;
        this.bien = bien;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public necesidadIndividual getNecesidad() {
        return necesidad;
    }

    public Donacion getDonacion() {
        return donacion;
    }

    public BienDonado getBien() {
        return bien;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public void setNecesidad(necesidadIndividual necesidad) {
        this.necesidad = necesidad;
    }
}
