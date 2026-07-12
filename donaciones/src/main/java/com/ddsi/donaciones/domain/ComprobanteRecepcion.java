package com.ddsi.donaciones.domain;

import java.util.Date;

public class ComprobanteRecepcion {
    private Date fechaHora;
    private String camion;

    public ComprobanteRecepcion(Date fechaHora, String camion) {
        this.fechaHora = fechaHora;
        this.camion = camion;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public String getCamion() {
        return camion;
    }

    public String toString() {
        return "En el momento " + fechaHora.toString() + " se ha entregado la donación mediante el camión " + camion;
    }
}
