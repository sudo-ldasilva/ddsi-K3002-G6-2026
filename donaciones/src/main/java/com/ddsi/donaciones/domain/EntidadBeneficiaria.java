package com.ddsi.donaciones.domain;

import java.util.ArrayList;

public class EntidadBeneficiaria {

    private String razonSocial;
    private String tipo;

    // ContactoTelefonoFijo / ContactoCelular?
    private ContactoTelefono telefono;

    private Direccion direccion;

    // ArrayList, LinkedList u otro?
    private ArrayList<Representante> representantes;
    private ArrayList<CampaniaNecesidad> necesidades;

    public EntidadBeneficiaria(
            String razonSocial, String tipo, ContactoTelefono telefono, Direccion direccion)
    {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.representantes = new ArrayList<Representante>();
        this.necesidades = new ArrayList<>();
    }

    public void agregarRepresentante(Representante representante) {
        representantes.add(representante);
    }

    public void quitarRepresentante(Representante representante) {
        representantes.remove(representante);
    }

    public ArrayList<CampaniaNecesidad> getNecesidades() {
        return necesidades;
    }

    public ArrayList<Representante> getRepresentantes() {
        return representantes;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public String getRazonSocial() {
        return razonSocial;
    }
}
