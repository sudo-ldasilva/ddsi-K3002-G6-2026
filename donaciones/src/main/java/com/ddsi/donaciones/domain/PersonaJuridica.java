package com.ddsi.donaciones.domain;
import java.util.List;
import java.util.ArrayList;


public class PersonaJuridica extends Donante {
    private String razonSocial;
    private TipoPersonaJuridica tipo;
    private String rubro;
    private ArrayList<Representante> representantes;
    private String cuit;

    public PersonaJuridica(String razonSocial, TipoPersonaJuridica tipo, String rubro, ArrayList<Representante> representantes, String cuit) {
        super();
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.rubro = rubro;
        this.representantes = representantes;
        this.cuit = cuit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public TipoPersonaJuridica getTipo() {
        return tipo;
    }

    public String getRubro() {
        return rubro;
    }

    public ArrayList<Representante> getRepresentantes() {
        return representantes;
    }

    public String getCuit() {
        return cuit;
    }

    public void agregarRepresentante(Representante representante){
        this.representantes.add(representante);
    }

    public void quitarRepresentante(Representante representante){
        this.representantes.remove(representante);
    }
}
