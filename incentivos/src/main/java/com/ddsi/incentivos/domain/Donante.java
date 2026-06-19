package com.ddsi.incentivos.domain;

import com.ddsi.incentivos.domain.dto.DonacionIndependienteDTO;
import com.ddsi.incentivos.services.DonacionesService;

import java.util.ArrayList;

public class Donante {
    private Contacto mail;
    private Categoria categoriaActual;
    private int indiceMisionActual;
    private ArrayList<Insignia> insignias;
    private boolean mostrarInsignias;

    public Donante(Contacto mail, Categoria categoriaActual, int indiceMisionActual, ArrayList<Insignia> insignias, boolean mostrarInsignias) {
        this.mail = mail;
        this.categoriaActual = categoriaActual;
        this.indiceMisionActual = indiceMisionActual;
        this.insignias = insignias;
        this.mostrarInsignias = mostrarInsignias;
    }

    public Contacto getMail() {
        return this.mail;
    }

    public Mision getMisionActual() {
        return this.categoriaActual.getMision(indiceMisionActual);
    }

    public ArrayList<DonacionIndependienteDTO> getDonaciones() {
        DonacionesService donacionesService = new DonacionesService();
        return donacionesService.getDonaciones(mail.getDireccion());
    }

    public void siguienteMision() { this.indiceMisionActual = this.indiceMisionActual + 1; }

    public Categoria getCategoriaActual() {
        return this.categoriaActual;
    }

    public void cambiarCategoria() throws Exception {
        if (!categoriaActual.puedePasarASiguienteCategoria(this)) {
            throw new Exception("No se puede pasar a la siguiente categoria");
        }

        categoriaActual = categoriaActual.getSiguiente();
        indiceMisionActual = 0;
    }

    public ArrayList<Insignia> getInsignias() {
        return this.insignias;
    }

    public void agregarInsignia(Insignia insignia) {
        insignias.add(insignia);
    }

    public ArrayList<Contacto> getMedioDeContacto() {
        DonacionesService donacionesService = new DonacionesService();
        return donacionesService.getMediosDeContacto(mail.getDireccion());
    }

    public boolean mostrarInsignias() {
        return this.mostrarInsignias;
    }

    public void setMostrarInsignias() {
        this.mostrarInsignias = true;
    }
}
