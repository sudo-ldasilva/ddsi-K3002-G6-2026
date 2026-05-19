package com.ddsi.donaciones.domain;

public class BienUsable extends Bien {
    private boolean esUsado;

    public BienUsable (String descripcion, String foto, Subcategoria subcategoria, boolean esUsado) {
        super(descripcion, foto, subcategoria);
        this.esUsado = esUsado;
    }

    public boolean getEsUsado() {
        return esUsado;
    }
}