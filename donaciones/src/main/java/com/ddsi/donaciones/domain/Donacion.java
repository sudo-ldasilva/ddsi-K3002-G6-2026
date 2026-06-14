package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.UUID;

public class Donacion {
    private UUID uuid;
    private Deposito deposito;
    private Donante donante;
    private String descripcion;
    private ArrayList<BienDonado> bienes;
    private boolean fueSegmentada;

    public Donacion(Deposito deposito, Donante donante, String descripcion, ArrayList<BienDonado> bienesDonados){
        this.uuid = UUID.randomUUID();
        this.deposito = deposito;
        this.donante = donante;
        this.descripcion = descripcion;
        this.bienes = bienesDonados;
        this.fueSegmentada = false;
    }

    public UUID getUUID() {
        return uuid;
    }

    public Deposito getDeposito(){
        return deposito;
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

    public void margarSegmentada() {
        fueSegmentada = true;
    }

    public void setDeposito(Deposito deposito){
        this.deposito = deposito;
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
