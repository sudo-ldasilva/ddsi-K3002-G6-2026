package com.ddsi.donaciones.domain;

public class BienDonado {
    private String cantidad;
    private String unidad;
    private Bien bien;

    public BienDonado(String cantidad, String unidad, Bien bien) {
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.bien = bien;
    }

    public String getCantidad() {
        return cantidad;
    }
    public String getUnidad() {
        return unidad;
    }
    public Bien getBien() {
        return bien;
    }
}
