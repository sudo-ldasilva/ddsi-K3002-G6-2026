package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.UUID;

public class EntidadBeneficiaria {

    private String razonSocial;
    private String tipo;
    private Contacto contacto;
    private Direccion direccion;
    private ArrayList<RepresentanteEntidadBeneficiaria> representantes;
    private ArrayList<CampaniaNecesidad> necesidades;

    //Constructor
    public EntidadBeneficiaria(String razonSocial, String tipo, Contacto contacto, Direccion direccion)
    {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.contacto = contacto;
        this.direccion = direccion;
        this.representantes = new ArrayList<RepresentanteEntidadBeneficiaria>();
        this.necesidades = new ArrayList<>();
    }

    //Getters
    public ArrayList<CampaniaNecesidad> getNecesidades() {
        return necesidades;
    }

    public ArrayList<RepresentanteEntidadBeneficiaria> getRepresentantes() {
        return representantes;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public String getTipo() {
        return tipo;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    //Setters
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void setRepresentantes(ArrayList<RepresentanteEntidadBeneficiaria> representantes) {
        this.representantes = representantes;
    }

    public void setNecesidades(ArrayList<CampaniaNecesidad> necesidades) {
        this.necesidades = necesidades;
    }

    //CRUD de Necesidades
    public void crearCampaniaNecesidad(CampaniaNecesidad campaniaNecesidad){
        necesidades.add(campaniaNecesidad);
    }

    public CampaniaNecesidad obtenerCampaniaNecesidad(UUID uuid){
        for (int i = 0; i < necesidades.size(); i++) {
            if (necesidades.get(i).getUuid().equals(uuid)) {
                return necesidades.get(i);
            }
        }
        return null;
    }

    public CampaniaNecesidad eliminarCampaniaNecesidad(UUID uuid){
        for (int i = 0; i < necesidades.size(); i++) {
            if (necesidades.get(i).getUuid().equals(uuid)) {
                return necesidades.remove(i);
            }
        }
        return null;
    }

    //Otros
    public void agregarRepresentante(RepresentanteEntidadBeneficiaria representante) {
        representantes.add(representante);
    }

    public void quitarRepresentante(RepresentanteEntidadBeneficiaria representante) {
        representantes.remove(representante);
    }



}
