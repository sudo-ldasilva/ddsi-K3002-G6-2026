package com.ddsi.donaciones.domain;
import java.util.ArrayList;
import com.ddsi.donaciones.domain.dto.*;


public class PersonaJuridica extends Donante {
    private String razonSocial;
    private TipoPersonaJuridica tipo;
    private String rubro;
    private ArrayList<RepresentantePersonaJuridica> representantes;

    public PersonaJuridica(PersonaJuridicaDTO persona) {
        this(persona.getMail(), persona.getDocumento(), persona.getRazonSocial(), persona.getTipo(), persona.getRubro());
        this.mediosDeContacto = persona.getMediosDeContacto();
    }

    public PersonaJuridica(String mail, Documento documento, String razonSocial, TipoPersonaJuridica tipo, String rubro) {
        super(mail, documento);
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.rubro = rubro;
        this.representantes = new ArrayList<RepresentantePersonaJuridica>();
    }

    public void setRepresentantes(ArrayList<RepresentantePersonaJuridica> representantes) {
        this.representantes = representantes;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public void setTipo(TipoPersonaJuridica tipo) {
        this.tipo = tipo;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
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

    public PersonaJuridicaDTO toDTO() {
        return new PersonaJuridicaDTO(mail, documento, razonSocial, tipo, rubro, mediosDeContacto);
    }
}
