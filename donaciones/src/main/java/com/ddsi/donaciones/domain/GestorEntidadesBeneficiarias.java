package com.ddsi.donaciones.domain;

import java.util.List;
import java.util.LinkedList;

public class GestorEntidadesBeneficiarias {
    private static GestorEntidadesBeneficiarias gestorEntidadesBeneficiarias = null;
    private List<EntidadBeneficiaria> entidadesBeneficiarias;

    private GestorEntidadesBeneficiarias() {
        this.entidadesBeneficiarias = new LinkedList<EntidadBeneficiaria>();
    }

    public static GestorEntidadesBeneficiarias getInstance(){
        if(gestorEntidadesBeneficiarias == null){
            gestorEntidadesBeneficiarias = new GestorEntidadesBeneficiarias();
        }

        return gestorEntidadesBeneficiarias;
    }

    public void agregarEntidadBeneficiaria(EntidadBeneficiaria entidadBeneficiaria){
        this.entidadesBeneficiarias.add(entidadBeneficiaria);
    }

    public void crearCampaniaNecesidad(EntidadBeneficiaria entidadBeneficiaria, CampaniaNecesidad campaniaNecesidad){
        entidadBeneficiaria.getNecesidades().add(campañaNecesidad);
    }

    public List<CampaniaNecesidad> obtenerCampaniasDeNecesidad(){
        List<CampaniaNecesidad> campanias = new LinkedList<CampaniaNecesidad>();
        this.entidadesBeneficiarias.forEach(e -> campanias.addAll(e.getNecesidades()));
        return campanias;
    }

}
