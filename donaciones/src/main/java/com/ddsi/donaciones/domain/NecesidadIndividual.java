package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.ToIntFunction;

public class NecesidadIndividual {
    private UUID uuid;
    private Bien bien;//que se necesita,ej)sillas,ropa,comida
    public int cantidadNecesaria;//cuanto se necesita
    public ArrayList<DonacionIndependiente> donaciones;//las donaciones independientes que se van juntando para satisfacer
    public CampaniaNecesidad campania;

    public NecesidadIndividual(UUID uuid, Bien bien, int cantidadNecesaria, CampaniaNecesidad campania) {
        this.uuid = UUID.randomUUID();
        this.bien = bien;
        this.cantidadNecesaria = cantidadNecesaria;
        this.campania = campania;
        this.donaciones = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getCantidadNecesaria() {
        return cantidadNecesaria;
    }

    public CampaniaNecesidad getCampania() {
        return campania;
    }

    public Bien getBien() {
        return bien;
    }
    
    public ArrayList<DonacionIndependiente> getDonaciones() {
        return donaciones;
    }

    public Subcategoria getSubcategoria() {
        return bien.getSubcategoria();
    }

    public Boolean estaCubierta() {
        return this.getCantidadRecibida() >= cantidadNecesaria;
    }

    public int getCantidadRecibida() {
        ToIntFunction<DonacionIndependiente> funcionGetCantidadBienes
            = d -> d.getBienes().stream().mapToInt(b -> b.getCantidad()).sum();

        return donaciones.stream().mapToInt(funcionGetCantidadBienes).sum();
    }

    public void recibir(DonacionIndependiente donacion) {
        donaciones.add(donacion);
    }
    public void reiniciar(){this.donaciones.clear();}
}
