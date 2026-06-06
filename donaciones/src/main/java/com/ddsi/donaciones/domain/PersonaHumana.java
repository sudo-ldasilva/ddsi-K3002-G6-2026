package com.ddsi.donaciones.domain;

import java.util.List;

public class PersonaHumana extends Donante{
    private String nombreYApellido;
    private int edad;
    private Genero genero;
    private Direccion direccion;
    private Contacto medioPredeterminado;

    public PersonaHumana(String documento, String nombreYApellido, int edad, Documento tipoDocumento, Genero genero, Direccion direccion, Contacto medioPredeterminado) {
        super(documento);
        this.nombreYApellido = nombreYApellido;
        this.edad = edad;
        this.tipoDocumento = tipoDocumento;
        //this.numeroDeDocumento = numeroDeDocumento; MARCOS: este valor es el que se le pasa a super, no?
        this.genero = genero;
        this.direccion = direccion;
        this.medioPredeterminado = medioPredeterminado;
    }

    public String getNombreYApellido() {
        return nombreYApellido;
    }

    public int getEdad() {
        return edad;
    }

    public Documento getTipoDocumento() {
        return tipoDocumento;
    }

    public Genero getGenero() {
        return genero;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public Contacto getMedioPredeterminado() {
        return medioPredeterminado;
    }

    public void setMedioPredeterminado(Contacto medioPredeterminado) throws Exception{
        if (!this.getContactos().contains(medioPredeterminado)){
            throw new Exception("Error: El medio de contacto no esta definido");
        }
        this.medioPredeterminado = medioPredeterminado;
    }
}
