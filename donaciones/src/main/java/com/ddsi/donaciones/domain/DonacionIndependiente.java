package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class DonacionIndependiente {
    private UUID uuid;
    private Subcategoria subcategoria;
    private NecesidadIndividual necesidad;
    private Donacion donacion;
    private ArrayList<BienDonado> bienes;
    private ArrayList<EstadoDonacion> historialEstados;
    private EstadoDonacion estadoActual;
    private java.util.Date fechaCreacion;

    public DonacionIndependiente(Subcategoria subcategoria, NecesidadIndividual necesidad, Donacion donacion) {
        this.uuid = UUID.randomUUID();
        this.subcategoria = subcategoria;
        this.necesidad = necesidad;
        this.donacion = donacion;
        this.bienes = new ArrayList<>();
        this.historialEstados = new ArrayList<>();
        this.estadoActual = new EstadoDonacion(EstadoDeDonacion.EN_DEPOSITO, new Date());
        this.fechaCreacion = new Date();
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

    public java.util.Date getFecha() {return fechaCreacion;}

    public void agregarBien(BienDonado bien) throws Exception{
        if(!bien.getBien().getSubcategoria().equals(this.subcategoria)) {
            throw new Exception();
        }
        bienes.add(bien);
    }

    public void setNecesidad(NecesidadIndividual necesidad) {
        this.necesidad = necesidad;
    }

    public void cambiarEstado(EstadoDeDonacion estado) {
        estadoActual = new EstadoDonacion(estado, new Date());
        historialEstados.add(estadoActual);
    }

    public void cambiarEstadoEntregaFallida(String justificacion) {
        estadoActual = new EstadoEntregaFallida(new Date(), justificacion);
        historialEstados.add(estadoActual);
    }
}
