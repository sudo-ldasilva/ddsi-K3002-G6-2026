package com.ddsi.donaciones.domain;

import com.ddsi.donaciones.domain.dto.BienDonadoDTO;

public class BienDonado {
    private int cantidad;
    private Bien bien;

    public BienDonado(BienDonadoDTO dto) {
        this(dto.getCantidad(), new Bien(dto.getBien()));
    }

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

    public BienDonadoDTO toDto() {
        return new BienDonadoDTO(
            cantidad,
            bien.toDto()
        );
    }
}
