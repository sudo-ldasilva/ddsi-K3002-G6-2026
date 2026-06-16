package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.UUID;

public class DonacionIndependiente {
    private UUID uuid;
    private Subcategoria subcategoria;
    private NecesidadIndividual necesidad;
    private Donacion donacion;
    private ArrayList<BienDonado> bienes;
    private ArrayList<EstadoDonacion> historialEstados;
    private EstadoDonacion estadoActual;

    public DonacionIndependiente(Subcategoria subcategoria, NecesidadIndividual necesidad, Donacion donacion) {
        this.uuid = UUID.randomUUID();
        this.subcategoria = subcategoria;
        this.necesidad = necesidad;
        this.donacion = donacion;
        this.bienes = new ArrayList<>();
        this.historialEstados = new ArrayList<>();
        this.estadoActual = EstadoDonacion.EN_DEPOSITO;

    }

    public UUID getUUID() {
        return this.uuid;
    }

    public EstadoDonacion getEstadoActual() {
        return this.estadoActual;
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

    public void cambiarEstado(EstadoDonacion estado) {
        historialEstados.add(estado);
        estadoActual = estado;
    }
}
