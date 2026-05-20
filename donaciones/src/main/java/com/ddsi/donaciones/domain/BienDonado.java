package com.ddsi.donaciones.domain;

public class BienDonado {
    private int cantidad;
    private String unidad;
    private Bien bien;
    private boolean asignado;

    public BienDonado(int cantidad, String unidad, Bien bien, boolean asignado) {
        this.cantidad = cantidad;
        this.unidad = unidad;
        this.bien = bien;
        this.asignado = asignado;
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
    public boolean estaAsignado() {
        return asignado;
    }
}
