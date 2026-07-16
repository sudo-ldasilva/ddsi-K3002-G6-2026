package com.ddsi.logistica.domain;

public class DonacionIndependiente {
    private float peso;
    private float volumen;
    private String razonSocial;
    private String direccionEntidad;

    public DonacionIndependiente(float peso, float volumen, String entidad, String direccionEntidad) {
        this.peso = peso;
        this.volumen = volumen;
        this.razonSocial = entidad;
        this.direccionEntidad = direccionEntidad;
    }

    public float getPeso() {
        return peso;
    }

    public float getVolumen() {
        return volumen;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getDireccionEntidad() {
        return direccionEntidad;
    }
}
