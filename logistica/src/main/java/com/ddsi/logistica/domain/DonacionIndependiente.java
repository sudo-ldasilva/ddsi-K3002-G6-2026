package com.ddsi.logistica.domain;

import java.util.UUID;

public class DonacionIndependiente {
    private UUID id;
    private float peso;
    private float volumen;
    private String razonSocial;
    private Direccion direccionEntidad;
    private Direccion direccionDeposito;

    public DonacionIndependiente(float peso, float volumen, String entidad, Direccion direccionEntidad, Direccion direccionDeposito) {
        this.id = UUID.randomUUID();
        this.peso = peso;
        this.volumen = volumen;
        this.razonSocial = entidad;
        this.direccionEntidad = direccionEntidad;
        this.direccionDeposito = direccionDeposito;
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

    public Direccion getDireccionEntidad() {
        return direccionEntidad;
    }

    public Direccion getDireccionDeposito() {
        return direccionDeposito;
    }
}
