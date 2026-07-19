package com.ddsi.donaciones.domain;

import java.awt.Image;
import java.util.Date;
import java.util.ArrayList;

public class ComprobanteRecepcion {
    private Date fechaHora;
    private String camion;
    private ArrayList<Image> fotos;

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

    public void cargarFoto(Image image) {
        fotos.add(image);
    }

    public void eliminarFoto(Image image) {
        fotos.remove(image);
    }

    public ArrayList<Image> getFotos() {
        return this.fotos;
    }
}
