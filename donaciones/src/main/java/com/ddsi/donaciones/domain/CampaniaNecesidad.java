package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.UUID;

public class CampaniaNecesidad {
    private UUID uuid;
    ArrayList<NecesidadIndividual> necesidades;
    EntidadBeneficiaria entidadBeneficiaria;
    String descripcion;
    String estado;

    public CampaniaNecesidad(EntidadBeneficiaria entidadBeneficiaria, String descripcion, String estado, Subcategoria subcategoria) {
        this.uuid = UUID.randomUUID();
        this.necesidades = new ArrayList<>();
        this.entidadBeneficiaria = entidadBeneficiaria;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    //Getters
    public UUID getUuid() {
        return uuid;
    }

    public ArrayList<NecesidadIndividual> getNecesidades() {
        return necesidades;
    }

    public EntidadBeneficiaria getEntidadBeneficiaria() {
        return entidadBeneficiaria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    //Setters
    public void setNecesidades(ArrayList<NecesidadIndividual> necesidades) {
        this.necesidades = necesidades;
    }

    public void setEntidadBeneficiaria(EntidadBeneficiaria entidadBeneficiaria) {
        this.entidadBeneficiaria = entidadBeneficiaria;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    //Otros
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
