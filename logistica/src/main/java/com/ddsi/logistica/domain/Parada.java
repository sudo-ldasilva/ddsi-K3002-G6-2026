package com.ddsi.logistica.domain;

import java.time.LocalDateTime;

public class Parada {
    private int nroOrden;
    private Entrega entrega;
    private LocalDateTime tiempoLlegada;

    public Parada(int nroOrden, Entrega entrega, LocalDateTime tiempoLlegada) {
        this.nroOrden = nroOrden;
        this.entrega = entrega;
        this.tiempoLlegada = tiempoLlegada;
    }

    public int getNroOrden() {
        return nroOrden;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public LocalDateTime getTiempoLlegada() {
        return tiempoLlegada;
    }
}
