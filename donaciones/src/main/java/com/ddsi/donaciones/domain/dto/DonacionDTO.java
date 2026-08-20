package com.ddsi.donaciones.domain.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import com.ddsi.donaciones.domain.Direccion;

public class DonacionDTO {
    private UUID uuid;
    private Direccion direccionDeposito;
    private String donante; // mail
    private String descripcion;
    private ArrayList<BienDonadoDTO> bienes;
    private boolean fueSegmentada;
    private LocalDate fecha;

    public DonacionDTO() { }

    public DonacionDTO(Direccion direccionDeposito, String donante, String descripcion, ArrayList<BienDonadoDTO> bienesDonados, LocalDate fecha){
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

    public String getDonante(){
        return donante;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public ArrayList<BienDonadoDTO> getBienes(){
        return bienes;
    }

    public boolean yaFueSegmentada() {
        return fueSegmentada;
    }
}
