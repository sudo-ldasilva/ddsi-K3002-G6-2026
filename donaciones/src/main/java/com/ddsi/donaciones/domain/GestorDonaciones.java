package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.UUID;

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

    public ArrayList<Donacion> getDonaciones() {
        return donaciones;
    }

    public ArrayList<DonacionIndependiente> getDonacionesIndependientes() {
        return posiblesDonaciones;
    }

    public boolean actualizarDonacion(Donacion nuevaDonacion) {
        for (int i = 0; i < donaciones.size(); i++) {
            if (donaciones.get(i).getUUID().equals(nuevaDonacion.getUUID())) {
                donaciones.set(i, nuevaDonacion);
                return true;
            }
        }
        return false;
    }

    public DonacionIndependiente getDonacionIndependienteByUUID(UUID uuid) {
        for (int i = 0; i < posiblesDonaciones.size(); i++) {
            DonacionIndependiente donacion = posiblesDonaciones.get(i);
            if (donacion.getUUID().equals(uuid)) {
                return donacion;
            }
        }
        return null;
    }

    public Donacion getDonacionByUUID(UUID uuid) {
        for (int i = 0; i < donaciones.size(); i++) {
            Donacion donacion = donaciones.get(i);
            if (donacion.getUUID().equals(uuid)) {
                return donacion;
            }
        }
        return null;
    }

    public Donacion eliminarDonacionByUUID(UUID uuid) {
        for (int i = 0; i < donaciones.size(); i++) {
            if (donaciones.get(i).getUUID().equals(uuid)) {
                Donacion donacion = donaciones.remove(i);
                return donacion;
            }
        }
        return null;
    }

    public void generarDonacionesIndependientes(Donacion donacion) throws Exception {
        if (donacion.yaFueSegmentada()) {
            throw new Exception("La donación ya fue segmentada");
        }

        ArrayList<DonacionIndependiente> donacionesInd = new ArrayList<>();

        for (BienDonado bien : donacion.getBienes()) {
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

    public void asignarDonacionIndependiente(DonacionIndependiente donacion, EntidadBeneficiaria entidad) {
        // en el TP dice que elige una entidad pero puede ser mejor que se asigne directamente a una campaña
        /*const CampaniaNecesidad campaniaElegida = this.elegirCampania(donacion, entidad);
          campanianiaElegida.getNecesidades()
          .find(necesidad -> necesidad.getBien().getSubcategoria() == donacion.getSubcategoria())
          .add(donacion);
        //*/}

}
