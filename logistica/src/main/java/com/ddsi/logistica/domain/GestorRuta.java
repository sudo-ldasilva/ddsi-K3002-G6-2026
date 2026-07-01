package com.ddsi.logistica.domain;

import java.util.ArrayList;

public class GestorRuta {
    private ArrayList<Ruta> rutas;
    private ArrayList<Camion> camiones;

    public GestorRuta(ArrayList<Ruta> rutas, ArrayList<Camion> camiones) {
        this.rutas = new ArrayList<>();
        this.camiones = new ArrayList<>();
    }

    public ArrayList<Ruta> crearRutas(ArrayList<DonacionIndependiente> donaciones) {
        // to do
        return null;
    }
}
