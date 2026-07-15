package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class CampaniaNecesidad {
    private UUID uuid;
    ArrayList<NecesidadIndividual> necesidades;
    EntidadBeneficiaria entidadBeneficiaria;
    String descripcion;

    protected CampaniaNecesidad(EntidadBeneficiaria entidadBeneficiaria, String descripcion, ArrayList<NecesidadIndividual> necesidades) {
        this.uuid = UUID.randomUUID();
        this.necesidades = necesidades;
        this.entidadBeneficiaria = entidadBeneficiaria;
        this.descripcion = descripcion;
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



    //Otros
    public boolean estaCubierta() {
        return necesidades.stream().allMatch(n -> n.estaCubierta());
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
    public boolean necesitaEstaSubcategoria(Subcategoria subcategoria) {
        return necesidades.stream().anyMatch(n -> !n.estaCubierta() && n.getSubcategoria().equals(subcategoria));
    }
}
