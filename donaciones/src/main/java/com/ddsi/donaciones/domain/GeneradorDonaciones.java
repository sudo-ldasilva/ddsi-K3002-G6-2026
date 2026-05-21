package com.ddsi.donaciones.domain;

import java.util.ArrayList;

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
