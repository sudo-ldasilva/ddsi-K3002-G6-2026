package com.ddsi.donaciones.domain;

import java.util.ArrayList;

public class GestorDonaciones {
    private static GestorDonaciones gestorDonaciones = null;
    private ArrayList<Donacion> donaciones;
    private ArrayList<DonacionIndependiente> posiblesDonaciones;

    private GestorDonaciones() {
        this.donaciones = new ArrayList<>();
        this.posiblesDonaciones = new ArrayList<>();
    }

    public static GestorDonaciones getInstance() {
        if (gestorDonaciones == null) {
            gestorDonaciones = new GestorDonaciones();
        }
        return gestorDonaciones;
    }

    public void donar(Donacion donacion) {
        donaciones.add(donacion);
    }

    public void generarDonacionesIndependientes(Donacion donacion) throws Exception {
        if (donacion.yaFueSegmentada()) {
            throw new Exception("La donación ya fue segmentada");
        }

        ArrayList<DonacionIndependiente> donacionesInd = new ArrayList<>();

        for (BienDonado bien : donacion.getBienesDonados()) {
            Subcategoria sc = bien.getBien().getSubcategoria();

            DonacionIndependiente donacionInd =
                donacionesInd
                .stream()
                .filter(d -> d.getSubcategoria().equals(sc))
                .findFirst()
                .orElse(null); // Devuelve el objeto si lo encuentra, o null si no

            if (donacionInd == null) {
                donacionInd = new DonacionIndependiente(sc, null, donacion);
                donacionesInd.add(donacionInd);
            }

            donacionInd.agregarBien(bien);
        }

        posiblesDonaciones.addAll(donacionesInd);
    }

}
