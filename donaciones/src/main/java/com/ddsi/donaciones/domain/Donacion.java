package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.UUID;

public class Donacion {
    private UUID uuid;
    private Direccion direccionDeposito;
    private Donante donante;
    private String descripcion;
    private ArrayList<BienDonado> bienes;
    private boolean fueSegmentada;


    public Donacion(Direccion direccionDeposito, Donante donante, String descripcion, ArrayList<BienDonado> bienesDonados){
        this.uuid = UUID.randomUUID();
        this.direccionDeposito = direccionDeposito;
        this.donante = donante;
        this.descripcion = descripcion;
        this.bienes = bienesDonados;
        this.fueSegmentada = false;
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
}
