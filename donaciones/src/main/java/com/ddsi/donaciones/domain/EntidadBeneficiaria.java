package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class EntidadBeneficiaria {

    private String razonSocial;
    private String tipo;
    private Contacto contacto;
    private Direccion direccion;
    private ArrayList<RepresentanteEntidadBeneficiaria> representantes;
    private ArrayList<CampaniaNecesidad> necesidades;
    private int cantidadDeDonacionesDelCuatrimestre;

    //Constructor
    public EntidadBeneficiaria(String razonSocial, String tipo, Contacto contacto, Direccion direccion)
    {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.contacto = contacto;
        this.direccion = direccion;
        this.representantes = new ArrayList<>();
        this.necesidades = new ArrayList<>();
        this.cantidadDeDonacionesDelCuatrimestre = 0;
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

    public int getCantidadDeDonacionesDelCuatrimestre() {return cantidadDeDonacionesDelCuatrimestre;}

    public ArrayList<CampaniaNecesidad> getNecesidadesPorSubcategoria(Subcategoria subcategoria) { return necesidades.stream()
            .filter(n -> n.necesitaEstaSubcategoria(subcategoria)).collect(Collectors.toCollection(ArrayList::new));}

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

    public void setCantidadDeDonacionesDelCuatrimestre(int nuevaCantidad) {this.cantidadDeDonacionesDelCuatrimestre = nuevaCantidad;}

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

    public void sumarDonacionCuatrimestral() {
        this.cantidadDeDonacionesDelCuatrimestre++;
    }

    public boolean necesitaEstaSubcategoria(Subcategoria subcategoria){
        return necesidades.stream().anyMatch(n -> n.necesitaEstaSubcategoria(subcategoria));
    }

}
