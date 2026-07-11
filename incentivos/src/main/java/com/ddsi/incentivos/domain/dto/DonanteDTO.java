package com.ddsi.incentivos.domain.dto;

import com.ddsi.incentivos.domain.*;
import com.ddsi.incentivos.services.DonacionesService;

import java.util.ArrayList;

public class DonanteDTO {
    private Contacto mail;
    private Categoria categoriaActual;
    private int indiceMisionActual;
    private ArrayList<Insignia> insignias;
    private boolean mostrarInsignias;

    public DonanteDTO(Contacto mail, Categoria categoriaActual, int indiceMisionActual, ArrayList<Insignia> insignias, boolean mostrarInsignias) {
        this.mail = mail;
        this.categoriaActual = categoriaActual;
        this.indiceMisionActual = indiceMisionActual;
        this.insignias = insignias;
        this.mostrarInsignias = mostrarInsignias;
    }

    public DonanteDTO(Donante donante) {
        this.mail = donante.getMail();
        this.categoriaActual = donante.getCategoriaActual();
        this.indiceMisionActual = donante.getIndiceMisionActual();
        this.insignias = donante.getInsignias();
        this.mostrarInsignias = donante.mostrarInsignias();
    }

    public Contacto getMail() {
        return this.mail;
    }

    public Mision getMisionActual() {
        return this.categoriaActual.getMision(indiceMisionActual);
    }

    public Categoria getCategoriaActual() {
        return this.categoriaActual;
    }

    public ArrayList<Insignia> getInsignias() {
        return this.insignias;
    }

    public void agregarInsignia(Insignia insignia) {
        insignias.add(insignia);
    }

    public boolean mostrarInsignias() {
        return this.mostrarInsignias;
    }

    public void setMostrarInsignias() {
        this.mostrarInsignias = true;
    }
}
