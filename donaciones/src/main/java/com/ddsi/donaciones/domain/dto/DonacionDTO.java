package com.ddsi.donaciones.domain.dto;

import java.util.ArrayList;

import com.ddsi.donaciones.domain.Deposito;
import com.ddsi.donaciones.domain.Donante;
import com.ddsi.donaciones.domain.BienDonado;

public class DonacionDTO {
    private Deposito deposito;
    private Donante donante;
    private String descripcion;
    private ArrayList<BienDonado> bienes;
    private boolean fueSegmentada;

    public DonacionDTO() { }

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

    public boolean getSegmentada() {
        return fueSegmentada;
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

    public void margarSegmentada() {
        fueSegmentada = true;
    }
}
