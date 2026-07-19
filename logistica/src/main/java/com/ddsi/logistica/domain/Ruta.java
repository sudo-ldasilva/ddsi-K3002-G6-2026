package com.ddsi.logistica.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Ruta {
    private UUID uuid;
    private ArrayList<Parada> paradas;
    private Camion camion;
    private LocalDateTime tiempoInicio;
    private LocalDateTime tiempoFin;
    private float kilometros;
    private int minutosDuracion;

    public Ruta(UUID uuid,ArrayList<Parada> paradas, Camion camion, LocalDateTime tiempoInicio, LocalDateTime tiempoFin, float kilometros, int minutosDuracion) {
        this.uuid = uuid;
        this.paradas = paradas;
        this.camion = camion;
        this.tiempoInicio = tiempoInicio;
        this.tiempoFin = tiempoFin;
        this.kilometros = kilometros;
        this.minutosDuracion = minutosDuracion;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public ArrayList<Parada> getParadas() {
        return paradas;
    }

    public Camion getCamion() {
        return camion;
    }

    public LocalDateTime getTiempoInicio() {
        return tiempoInicio;
    }

    public LocalDateTime getTiempoFin() {
        return tiempoFin;
    }

    public float getKilometros() {
        return kilometros;
    }

    public int getMinutosDuracion() {
        return minutosDuracion;
    }
}
