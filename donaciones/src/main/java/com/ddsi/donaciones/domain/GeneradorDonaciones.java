package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.List;

public class GeneradorDonaciones {
    private static GeneradorDonaciones generadorDonaciones = null;
    private ArrayList<Donacion> donaciones;
    private ArrayList<DonacionIndependiente> posiblesDonaciones;

    private GeneradorDonaciones() {
        this.donaciones = new ArrayList<>();
        this.posiblesDonaciones = new ArrayList<>();
    }

    public static GeneradorDonaciones getInstance() {
        if (generadorDonaciones == null) {
            generadorDonaciones = new GeneradorDonaciones();
        }
        return generadorDonaciones;
    }

    public void donar(Donacion donacion) {
        // TODO
    }

    private void generarDonacionesIndependientes(Donacion donacion) {
        // TODO
    }

}
