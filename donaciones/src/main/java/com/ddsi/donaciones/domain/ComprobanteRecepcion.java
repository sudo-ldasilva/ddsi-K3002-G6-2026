package com.ddsi.donaciones.domain;

import java.util.Date;
import java.util.ArrayList;

public class ComprobanteRecepcion {
    private Date fechaHora;
    private String camion;
    private ArrayList<String> fotos;

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

    public void cargarFoto(String url) {
        fotos.add(url);
    }

    public void eliminarFoto(String url) {
        fotos.remove(url);
    }

    public ArrayList<String> getFotos() {
        return this.fotos;
    }
}
