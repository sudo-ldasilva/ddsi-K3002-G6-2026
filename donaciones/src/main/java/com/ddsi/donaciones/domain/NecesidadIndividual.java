package com.ddsi.donaciones.domain;

import java.util.ArrayList;

public class NecesidadIndividual {
    private Bien bien;//que se necesita,ej)sillas,ropa,comida
    public int cantidadNecesaria;//cuanto se necesita
    public int cantidadActual;
    public ArrayList<DonacionIndependiente> donaciones;//las donaciones independientes que se van juntando para satisfacer
    public Boolean estado;

    public NecesidadIndividual(Bien bien, int casntidadNecesaria) {
        this.bien = bien;
        this.cantidadNecesaria = casntidadNecesaria;
        this.cantidadActual = 0;
        this.donaciones = new ArrayList<>();
    }

    public Bien getBien() {
        return bien;
    }

    public int getCasntidadNecesaria() {
        return cantidadNecesaria;
    }

    public ArrayList<DonacionIndependiente> getDonaciones() {
        return donaciones;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Boolean estaCubierta() {
        /*
        boolean cubierto = donaciones.stream().mapToInt(d -> d.getBien().getCantidad()).sum() >= cantidadNecesaria;//conviete a stream,lleva el campo unidad minima de bien a int y suma todo, desp compara con la cantidad necesaria
        return cubierto;
        //*/
        // Alguien corrija el error de compilación de arriba xfi :D
        return false;
    }

    public int setCantidadActual() {
        /*
        this.cantidadActual = donaciones.stream().mapToInt(d -> d.getBien().getCantidad()).sum();
        return cantidadActual;
        //*/
        // Alguien corrija el error de compilación de arriba xfi :D
        return -1;
    }
}
