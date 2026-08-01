package com.ddsi.donaciones.domain.dto;

import com.ddsi.donaciones.domain.*;
import java.util.ArrayList;

public class PersonaJuridicaDTO {
    private Contacto mail;
    private ArrayList<Contacto> mediosDeContacto;
    private Documento documento;
    private String razonSocial;
    private TipoPersonaJuridica tipo;
    private String rubro;

    public PersonaJuridicaDTO() {}
    public PersonaJuridicaDTO(Contacto mail, Documento documento, String razonSocial, TipoPersonaJuridica tipo, String rubro, ArrayList<Contacto> mediosDeContacto) {
        this.mail = mail;
        this.documento = documento;
        this.razonSocial = razonSocial;
        this.tipo = tipo;
        this.rubro = rubro;
        this.mediosDeContacto = mediosDeContacto;
    }

    public Contacto getMail() { return mail; };
    public ArrayList<Contacto> getMediosDeContacto() { return mediosDeContacto; };
    public Documento getDocumento() { return documento; };
    public String getRazonSocial() { return razonSocial; };
    public TipoPersonaJuridica getTipo() { return tipo; };
    public String getRubro() { return rubro; };
}
