package com.ddsi.donaciones.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ddsi.donaciones.domain.dto.DonacionDTO;

public class Donacion {
    private UUID uuid;
    private Direccion direccionDeposito;
    private Donante donante;
    private String descripcion;
    private ArrayList<BienDonado> bienes;
    private boolean fueSegmentada;
    private LocalDate fecha;

    public Donacion(DonacionDTO dto) throws Exception{
        Donante donante = GestorDonantes.getInstance().getDonante(dto.getDonante());
        if (donante == null) throw new Exception("Donante no encontrado");

        this(
            dto.getDireccionDeposito(),
            donante,
            dto.getDescripcion(),
            dto.getBienes().stream().map( b -> new BienDonado(b) ).collect(Collectors.toCollection(ArrayList::new)),
            dto.getFecha()
        );
    }

    public Donacion(Direccion direccionDeposito, Donante donante, String descripcion, ArrayList<BienDonado> bienesDonados, LocalDate fecha){
        this.uuid = UUID.randomUUID();
        this.direccionDeposito = direccionDeposito;
        this.donante = donante;
        this.descripcion = descripcion;
        this.bienes = bienesDonados;
        this.fueSegmentada = false;
        this.fecha = fecha;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public UUID getUUID() {
        return uuid;
    }

    public Direccion getDireccionDeposito(){
        return direccionDeposito;
    }

    public Donante getDonante(){
        return donante;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public ArrayList<BienDonado> getBienes(){
        return bienes;
    }

    public boolean yaFueSegmentada() {
        return fueSegmentada;
    }

    public void marcarSegmentada() {
        fueSegmentada = true;
    }

    public void setFueSegmentada(boolean segmentada) {
        this.fueSegmentada = segmentada;
    }

    public void setDeposito(Direccion direccionDeposito){
        this.direccionDeposito = direccionDeposito;
    }

    public void setDonante(Donante donante){
        this.donante = donante;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public void setBienes(ArrayList<BienDonado> bienes){
        this.bienes = bienes;
    }

    public DonacionDTO toDto() {
    // public DonacionDTO(Direccion direccionDeposito, String donante, String descripcion, ArrayList<BienDonadoDTO> bienesDonados, Date fecha){
        return new DonacionDTO(
            direccionDeposito,
            donante.getMail(),
            descripcion,
            bienes.stream().map( b -> b.toDto() ).collect(Collectors.toCollection(ArrayList::new)),
            fecha
        );
    }
}
