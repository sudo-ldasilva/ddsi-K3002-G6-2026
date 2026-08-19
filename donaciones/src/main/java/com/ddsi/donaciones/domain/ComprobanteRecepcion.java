package com.ddsi.donaciones.domain;

import java.time.LocalDate;
import java.util.ArrayList;

public class ComprobanteRecepcion {
    private LocalDate fechaHora;
    private String camion;
    private ArrayList<String> fotos;

    public ComprobanteRecepcion(LocalDate fechaHora, String camion) {
        this.fechaHora = fechaHora;
        this.camion = camion;
    }

    public LocalDate getFechaHora() {
        return fechaHora;
    }

    public String getCamion() {
        return camion;
    }

    public String toString() {
        return "En el momento " + fechaHora.toString() + " se ha entregado la donación mediante el camión " + camion;
    }

    public void cargarFoto(String image) {
        fotos.add(image);
    }

    public void eliminarFoto(String image) {
        fotos.remove(image);
    }

    public ArrayList<String> getFotos() {
        return this.fotos;
    }
}
