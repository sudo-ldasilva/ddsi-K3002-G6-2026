package com.ddsi.incentivos.domain;

import com.ddsi.incentivos.domain.dto.DonacionIndependienteDTO;

import java.util.ArrayList;
import java.time.LocalDate;

public abstract class Mision {
    private String nombre;
    private int cantidadNecesaria;
    private String unidadDeMedida;

    public Insignia misionCumplida(Donante donante, ArrayList<DonacionIndependienteDTO> donaciones) {
        if (getProgresoActual(donante, donaciones) == cantidadNecesaria){
            return new Insignia(LocalDate.now(), this);
        }
        return null;
    }

    public int getCantidadNecesaria() {
        return cantidadNecesaria;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public abstract int getProgresoActual(Donante donante, ArrayList<DonacionIndependienteDTO> donaciones);

    public String getNombre() {
        return this.nombre;
    }
}
