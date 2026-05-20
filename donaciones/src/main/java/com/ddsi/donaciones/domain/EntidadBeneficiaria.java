package com.ddsi.donaciones.domain;

import java.util.ArrayList;

public class EntidadBeneficiaria {

    private String razonSocial;
    private String tipo;

    // ContactoTelefonoFijo / ContactoCelular?
    private String telefono;

    private Direccion direccion;

    // ArrayList, LinkedList u otro?
    private ArrayList<Representante> representantes;
    private ArrayList<CampañaNecesidad> necesidades;

    public EntidadBeneficiaria(
            String razonSocial, String tipo, String telefono, Direccion direccion)
    {
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.representantes = new ArrayList<Representante>();
        this.necesidades = new ArrayList<Necesidad>();
    }

    public void agregarRepresentante(Representante representante) {
        representantes.add(representante);
    }

    public void quitarRepresentante(Representante representante) {
        representantes.remove(representante);
    }
}
