package com.ddsi.donaciones.domain;
import java.util.ArrayList;


public class PersonaJuridica extends Donante {
    private String razonSocial;
    private TipoPersonaJuridica tipo;
    private String rubro;
    private ArrayList<RepresentantePersonaJuridica> representantes;

    public PersonaJuridica(Documento documento, String razonSocial, TipoPersonaJuridica tipo, String rubro) {
        super(documento);
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.rubro = rubro;
        this.representantes = new ArrayList<RepresentantePersonaJuridica>();
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

    public ArrayList<RepresentantePersonaJuridica> getRepresentantes() {
        return representantes;
    }

    public void agregarRepresentante(RepresentantePersonaJuridica representante){
        this.representantes.add(representante);
    }

    public void quitarRepresentante(RepresentantePersonaJuridica representante){
        this.representantes.remove(representante);
    }
}
