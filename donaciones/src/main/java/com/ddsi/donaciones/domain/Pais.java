package com.ddsi.donaciones.domain;

public class Pais{
    private String nombre;

    public Pais(String nombre) {
        this.nombre = nombre;
    }

    public String getPais(){
        return this.nombre;
    }
}