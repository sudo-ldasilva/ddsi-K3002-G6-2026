package com.ddsi.donaciones.domain;

import java.util.ArrayList;

public class Deposito {
    private Direccion direccion;
    private ArrayList<Donacion> donaciones;

    public Deposito(Direccion direccion){
        this.direccion = direccion;
    }

    public Direccion getDireccion(){
        return direccion;
    }

    public void ingresarDonacion(Donacion donacion) {
        donaciones.add(donacion);
    }

    public void despacharDonacion(Donacion donacion) {
        donaciones.remove(donacion);
    }
}
