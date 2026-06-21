package com.ddsi.donaciones.domain;

public class PersonaHumana extends Donante{
    private String nombreYApellido;
    private int edad;
    private Genero genero;
    private Direccion direccion;
    private Contacto medioPredeterminado;

    public PersonaHumana(Contacto mail, Documento documento, String nombreYApellido, int edad, Genero genero, Direccion direccion, Contacto medioPredeterminado) {
        super(mail, documento);
        this.nombreYApellido = nombreYApellido;
        this.edad = edad;
        this.genero = genero;
        this.direccion = direccion;
        this.medioPredeterminado = medioPredeterminado;
    }

    public String getNombreYApellido() {
        return nombreYApellido;
    }

    public void setNombreYApellido(String nombreYApellido) {
        this.nombreYApellido = nombreYApellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public int getEdad() {
        return edad;
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
            throw new Exception(); //El medio de contacto no esta definido (usar codificacion o booleano, no devolver mensaje de error)
        }
        this.medioPredeterminado = medioPredeterminado;
    }
}
