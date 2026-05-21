package com.ddsi.donaciones.domain;

import java.util.ArrayList;

public class CampañaNecesidad {
    ArrayList<NecesidadIndividual> necesidades;
    EntidadBeneficiaria entidadBeneficiaria;
    String descripcion;
    String estado;
    Subcategoria subcategoria;

    public CampañaNecesidad(EntidadBeneficiaria entidadBeneficiaria, String descripcion, String estado, Subcategoria subcategoria) {
        this.necesidades = new ArrayList<>();
        this.entidadBeneficiaria = entidadBeneficiaria;
        this.descripcion = descripcion;
        this.estado = estado;
        this.subcategoria = subcategoria;
    }

    public boolean estaCubierta() {
        return necesidades
            .stream()
            .filter(n -> !n.estaCubierta())
            .findAny()
            .isEmpty();
    }

    public void agregarNecesidad(NecesidadIndividual necesidad) {
        necesidades.add(necesidad);
    }
}
