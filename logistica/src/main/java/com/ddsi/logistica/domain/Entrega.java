package com.ddsi.logistica.domain;

import java.awt.*;
import java.util.ArrayList;

public class Entrega {
    private ArrayList<DonacionIndependiente> donaciones;
    private String razonSocial;
    private String direccionEntidad;
    private float peso;
    private float volumen;
    private Image fotoConfirmacion;
    private Estado estadoEntrega;
    private Ruta ruta;

    public Entrega(ArrayList<DonacionIndependiente> donaciones, String razonSocial, String direccionEntidad, float peso, float volumen) {
        this.donaciones = new ArrayList<>();
        this.razonSocial = razonSocial;
        this.direccionEntidad = direccionEntidad;
        this.peso = peso;
        this.volumen = volumen;
        this.estadoEntrega = Estado.PENDIENTE;
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

    public Ruta getRuta() {
        return ruta;
    }
}
