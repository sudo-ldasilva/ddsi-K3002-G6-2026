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

    public void crearCampañaNecesidad(EntidadBeneficiaria entidadBeneficiaria, CampañaNecesidad campañaNecesidad){
        entidadBeneficiaria.getNecesidades().add(campañaNecesidad);
    }

    public List<CampañaNecesidad> obtenerCampañasDeNecesidad(){
        List<CampañaNecesidad> campañas = new LinkedList<CampañaNecesidad>();
        this.entidadesBeneficiarias.forEach(e -> campañas.addAll(e.getNecesidades()));
        return campañas;
    }

}
