package com.ddsi.donaciones.domain;

public class BienDonado {
    private int cantidad;
    private String unidad;
    private Bien bien;

    public BienDonado(int cantidad, String unidad, Bien bien) {
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.bien = bien;
    }

    public int getCantidad() {
        return cantidad;
    }
    public String getUnidad() {
        return unidad;
    }
    public Bien getBien() {
        return bien;
    }
}
