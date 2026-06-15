package com.ddsi.donaciones.domain;

import java.util.ArrayList;

public class EntidadBeneficiaria {

    private String razonSocial;
    private String tipo;

    // ContactoTelefonoFijo / ContactoCelular?
    private Contacto contacto;

    private Direccion direccion;

    // ArrayList, LinkedList u otro?
    private ArrayList<RepresentanteEntidadBeneficiaria> representantes;
    private ArrayList<CampaniaNecesidad> necesidades;

    public EntidadBeneficiaria(
            String razonSocial, String tipo, Contacto contacto, Direccion direccion)
    {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.contacto = contacto;
        this.direccion = direccion;
        this.representantes = new ArrayList<RepresentanteEntidadBeneficiaria>();
        this.necesidades = new ArrayList<>();
    }

    public void agregarRepresentante(RepresentanteEntidadBeneficiaria representante) {
        representantes.add(representante);
    }

    public void quitarRepresentante(RepresentanteEntidadBeneficiaria representante) {
        representantes.remove(representante);
    }

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
}
