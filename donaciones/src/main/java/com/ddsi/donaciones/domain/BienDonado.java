package com.ddsi.donaciones.domain;

public class BienDonado {
    private int cantidad;
    private Bien bien;

    public BienDonado(int cantidad, Bien bien) {
        this.cantidad = cantidad;
        this.bien = bien;
    }

    public int getCantidad() {
        return cantidad;
    }
    public Bien getBien() {
        return bien;
    }
}
