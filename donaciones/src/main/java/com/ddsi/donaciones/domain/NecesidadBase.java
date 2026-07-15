package com.ddsi.donaciones.domain;

public class NecesidadBase {
    private Bien bien;//que se necesita,ej)sillas,ropa,comida
    public int cantidadNecesaria;//cuanto se necesita

    public NecesidadBase(Bien bien, int cantidadNecesaria) {
        this.bien = bien;
        this.cantidadNecesaria = cantidadNecesaria;
    }

    public Bien getBien() {
        return bien;
    }

    public int getCantidadNecesaria() {
        return cantidadNecesaria;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public void setCantidadNecesaria(int cantidadNecesaria) {
        this.cantidadNecesaria = cantidadNecesaria;
    }
}
