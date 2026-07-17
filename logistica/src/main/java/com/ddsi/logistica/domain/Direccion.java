package com.ddsi.logistica.domain;

public class Direccion {
    private String direccion;
    private float latitud;
    private float longitud;

    public Direccion(String direccion, float latitud, float longitud) {
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public float getLatitud() {
        return latitud;
    }

    public float getLongitud() {
        return longitud;
    }
}
