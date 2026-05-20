package com.ddsi.donaciones.domain;

import java.util.List;

public class PersonaHumana extends Donante{
    private String nombreYApellido;
    private int edad;
    private Documento tipoDocumento;
    private String numeroDocumento;
    private Genero genero;
    private Direccion direccion;
    private Contacto medioPredeterminado;

    public PersonaHumana(List<Contacto> mediosDeContacto, String nombreYApellido, int edad, Documento tipoDocumento, String numeroDocumento, Genero genero, Direccion direccion, Contacto medioPredeterminado) {
        super(mediosDeContacto);
        this.nombreYApellido = nombreYApellido;
        this.edad = edad;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
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

    public String getNumeroDocumento() {
        return numeroDocumento;
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

    public void setMedioPredeterminado(Contacto medioPredeterminado) {
        this.medioPredeterminado = medioPredeterminado;
    }
}
