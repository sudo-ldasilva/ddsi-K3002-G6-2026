package com.ddsi.logistica.domain;

import java.awt.*;
import java.util.ArrayList;
import java.util.UUID;

public class Entrega {
    private UUID id;
    private ArrayList<DonacionIndependiente> donaciones;
    private String razonSocial;
    private Direccion direccionEntidad;
    private Direccion direccionDeposito;
    private float peso;
    private float volumen;
    private Image fotoConfirmacion;
    private Estado estadoEntrega;
    private Parada parada;

    public Entrega(ArrayList<DonacionIndependiente> donaciones, String razonSocial, Direccion direccionEntidad, Direccion direccionDeposito,float peso, float volumen) {
        this.id = UUID.randomUUID();
        this.donaciones = new ArrayList<>();
        this.razonSocial = razonSocial;
        this.direccionEntidad = direccionEntidad;
        this.direccionDeposito = direccionDeposito;
        this.peso = peso;
        this.volumen = volumen;
        this.estadoEntrega = Estado.PENDIENTE;
        this.parada = null;
    }

    public float getPeso() {
        return peso;
    }

    public float getVolumen() {
        return volumen;
    }

    public void setEstadoEntrega(Estado estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }

    public Parada getParada() {
        return parada;
    }

    public Direccion getDireccionEntidad() {
        return direccionEntidad;
    }

    public Direccion getDireccionDeposito() {
        return direccionDeposito;
    }
}
