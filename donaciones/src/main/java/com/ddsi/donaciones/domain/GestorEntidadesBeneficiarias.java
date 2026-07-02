package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.UUID;

public class GestorEntidadesBeneficiarias {
    private static GestorEntidadesBeneficiarias gestorEntidadesBeneficiarias = null;
    private ArrayList<EntidadBeneficiaria> entidadesBeneficiarias;

    //Constructor
    private GestorEntidadesBeneficiarias() {
        this.entidadesBeneficiarias = new ArrayList<EntidadBeneficiaria>();
    }

    //Singleton
    public static GestorEntidadesBeneficiarias getInstance(){
        if(gestorEntidadesBeneficiarias == null){
            gestorEntidadesBeneficiarias = new GestorEntidadesBeneficiarias();
        }

        return gestorEntidadesBeneficiarias;
    }

    //CRUD
    public ArrayList<EntidadBeneficiaria> getEntidadesBeneficiarias() {
        return entidadesBeneficiarias;
    }

    public EntidadBeneficiaria getEntidad(String telefono){
        for (int i = 0; i < entidadesBeneficiarias.size(); i++) {
            if (entidadesBeneficiarias.get(i).getContacto().getDireccion().equals(telefono)){
                return entidadesBeneficiarias.get(i);
            }
        }
        return null;
    }

    public EntidadBeneficiaria eliminarEntidad(String telefono){
        for (int i = 0; i < entidadesBeneficiarias.size(); i++) {
            if (entidadesBeneficiarias.get(i).getContacto().getDireccion().equals(telefono)) {
                return entidadesBeneficiarias.remove(i);
            }
        }
        return null;
    }

    public void agregarEntidadBeneficiaria(EntidadBeneficiaria entidadBeneficiaria){
        this.entidadesBeneficiarias.add(entidadBeneficiaria);
    }



    public ArrayList<CampaniaNecesidad> obtenerCampaniasDeNecesidad(){
        ArrayList<CampaniaNecesidad> campanias = new ArrayList<CampaniaNecesidad>();
        this.entidadesBeneficiarias.forEach(e -> campanias.addAll(e.getNecesidades()));
        return campanias;
    }

    public CampaniaNecesidad obtenerCampaniaDeNecesidad(UUID id){
        for (int i = 0; i < entidadesBeneficiarias.size(); i++) {
            for (int j = 0; j < entidadesBeneficiarias.get(i).getNecesidades().size(); j++) {
                if(entidadesBeneficiarias.get(i).getNecesidades().get(j).getUuid().equals(id)){
                    return entidadesBeneficiarias.get(i).getNecesidades().get(j);
                }
            }
        }
        return null;
    }

    public void reiniciarContadoresDeDonaciones(){
        entidadesBeneficiarias.forEach(e->e.setCantidadDeDonacionesDelCuatrimestre(0));
    }


}
