package com.ddsi.logistica.domain;

public class DonacionIndependiente {
    private float peso;
    private float volumen;
    private String entidad;
    private String direccionEntidad;

    public DonacionIndependiente(float peso, float volumen, String entidad, String direccionEntidad) {
        this.peso = peso;
        this.volumen = volumen;
        this.entidad = entidad;
        this.direccionEntidad = direccionEntidad;
    }

    public float getPeso() {
        return peso;
    }

    public float getVolumen() {
        return volumen;
    }

    public String getEntidad() {
        return entidad;
    }

    public String getDireccionEntidad() {
        return direccionEntidad;
    }
}
