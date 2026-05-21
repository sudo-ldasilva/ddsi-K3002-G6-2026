package com.ddsi.donaciones.domain;
import java.util.List;
import java.util.ArrayList;


public class PersonaJuridica extends Donante {
    private String razonSocial;
    private TipoPersonaJuridica tipo;
    private String rubro;
    private ArrayList<Representante> representantes;

    public PersonaJuridica(String documento, String razonSocial, TipoPersonaJuridica tipo, String rubro) {
        super(documento);
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.rubro = rubro;
        this.representantes = new ArrayList<Representante>();
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

    public void agregarRepresentante(Representante representante){
        this.representantes.add(representante);
    }

    public void quitarRepresentante(Representante representante){
        this.representantes.remove(representante);
    }
}
