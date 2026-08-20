package com.ddsi.donaciones.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import com.ddsi.donaciones.domain.dto.DonacionIndependienteDTO;

public class DonacionIndependiente {
    private UUID uuid;
    private Subcategoria subcategoria;
    private NecesidadIndividual necesidad;
    private Donacion donacion;
    private ArrayList<BienDonado> bienes;
    private ArrayList<EstadoDonacion> historialEstados;
    private LocalDate fechaCreacion;
    private ComprobanteRecepcion comprobante;

    public DonacionIndependiente(Subcategoria subcategoria, NecesidadIndividual necesidad, Donacion donacion) {
        this.uuid = UUID.randomUUID();
        this.subcategoria = subcategoria;
        this.necesidad = necesidad;
        this.donacion = donacion;
        this.bienes = new ArrayList<>();
        this.fechaCreacion = LocalDate.now();

        this.historialEstados = new ArrayList<>();
        this.historialEstados.add(new EstadoDonacion(EstadoDeDonacion.EN_DEPOSITO, LocalDate.now()));
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public EstadoDonacion getEstadoActual() {
        return this.historialEstados.getLast();
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

    public LocalDate getFecha() {return fechaCreacion;}

    public void agregarBien(BienDonado bien){
        bienes.add(bien);
    }

    public void setNecesidad(NecesidadIndividual necesidad) {
        this.necesidad = necesidad;
    }

    public void cambiarEstado(EstadoDonacion estado) {
        historialEstados.add(estado);
    }

    public ComprobanteRecepcion getComprobante() {
        return this.comprobante;
    }

    public void setComprobante(ComprobanteRecepcion comprobante) {
        this.comprobante = comprobante;
    }

    public void agregarFotoRecepcion(String foto) {
        comprobante.cargarFoto(foto);
    }

    public DonacionIndependienteDTO toDto() {
        return new DonacionIndependienteDTO(uuid, subcategoria.getNombre(), donacion.getDonante().getMail(), bienes.size(), historialEstados.getLast(), fechaCreacion, comprobante);
    }

}
