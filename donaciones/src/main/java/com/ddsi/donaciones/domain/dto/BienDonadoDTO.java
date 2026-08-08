package com.ddsi.donaciones.domain.dto;

public class BienDonadoDTO {
    private int cantidad;
    private BienDTO bien;

    public BienDonadoDTO(int cantidad, BienDTO bien) {
        this.cantidad = cantidad;
        this.bien = bien;
    }

    public int getCantidad() {
        return cantidad;
    }
    public BienDTO getBien() {
        return bien;
    }
}
