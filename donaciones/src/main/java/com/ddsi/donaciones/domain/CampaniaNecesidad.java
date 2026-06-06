package com.ddsi.donaciones.domain;

import java.util.ArrayList;

public class CampaniaNecesidad {
    ArrayList<NecesidadIndividual> necesidades;
    EntidadBeneficiaria entidadBeneficiaria;
    String descripcion;
    String estado;

    public CampaniaNecesidad(EntidadBeneficiaria entidadBeneficiaria, String descripcion, String estado, Subcategoria subcategoria) {
        this.necesidades = new ArrayList<>();
        this.entidadBeneficiaria = entidadBeneficiaria;
        this.descripcion = descripcion;
        this.estado = estado;
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

    public ArrayList<Subcategoria> getSubcategorias() {
        ArrayList<Subcategoria> list = new ArrayList<>();
        for (NecesidadIndividual necesidad : necesidades) {
            list.add(necesidad.getSubcategoria());
        }
        return list;
    }
}
