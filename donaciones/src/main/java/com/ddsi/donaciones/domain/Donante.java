package com.ddsi.donaciones.domain;
import java.util.List;
import java.util.LinkedList;


public abstract class Donante {
    private List<Contacto> mediosDeContacto;

    public Donante(List<Contacto> mediosDeContacto) {
        this.mediosDeContacto = new LinkedList<Contacto>();
    }

    public List<Contacto> getMediosDeContacto() {
        return mediosDeContacto;
    }

    public void agregarContacto(Contacto contacto){
        this.mediosDeContacto.add(contacto);
    }

    public void eliminarContacto(Contacto contacto){
        this.mediosDeContacto.remove(contacto);
    }
}
