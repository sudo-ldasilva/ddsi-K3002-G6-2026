package com.ddsi.donaciones.domain;
import java.util.ArrayList;

public class Donacion {
    private Deposito deposito;
    private Donante donante;
    private String descripcion;
    private ArrayList<BienDonado> bienes;
    private boolean fueSegmentada;

    public Donacion(Deposito deposito, Donante donante, String descripcion, ArrayList<BienDonado> bienesDonados){
        this.deposito = deposito;
        this.donante = donante;
        this.descripcion = descripcion;
        this.bienes = bienesDonados;
        this.fueSegmentada = false;
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
}
