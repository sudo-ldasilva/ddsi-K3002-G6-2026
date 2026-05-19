package com.ddsi.donaciones.domain;

public class Deposito {
    private Direccion direccion;

    public Deposito(Direccion direccion){
        this.direccion = direccion;
    }

    public Direccion getDireccion(){
        return direccion;
    }
}
