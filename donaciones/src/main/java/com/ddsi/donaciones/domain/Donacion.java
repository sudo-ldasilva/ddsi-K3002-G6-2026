package com.ddsi.donaciones.domain;
import java.util.ArrayList;

public class Donacion {
    private Deposito deposito;
    private Donante donante;
    private String descripcion;
    private ArrayList<BienDonado> bienesDonados;
    private boolean fueSegmentada;

    public Donacion(Deposito deposito, Donante donante, String descripcion, ArrayList<BienDonado> bienesDonados){
        this.deposito = deposito;
        this.donante = donante;
        this.descripcion = descripcion;
        this.bienesDonados = bienesDonados;
        this.fueSegmentada = false;
    }

    public Deposito getDeposito(){
        return deposito;
    }

    public Donante getDonante(){
        return donante;
    }

    public ArrayList<BienDonado> getBienesDonados(){
        return bienesDonados;
    }

    public boolean yaFueSegmentada() {
        return fueSegmentada;
    }

    public void segmentada() {
        fueSegmentada = true;
    }
}
