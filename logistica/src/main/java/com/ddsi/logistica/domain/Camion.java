package com.ddsi.logistica.domain;

public class Camion {
    private String patente;
    private float capacidadVolumen;
    private float capacidadCarga;
    private float altura;

    public Camion(String patente, float capacidadVolumen, float capacidadCarga, float altura) {
        this.patente = patente;
        this.capacidadVolumen = capacidadVolumen;
        this.capacidadCarga = capacidadCarga;
        this.altura = altura;
    }

    public String getPatente() {
        return patente;
    }

    public float getCapacidadVolumen() {
        return capacidadVolumen;
    }

    public float getCapacidadCarga() {
        return capacidadCarga;
    }

    public float getAltura() {
        return altura;
    }
}
