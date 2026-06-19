package com.ddsi.incentivos.domain;
import java.util.Date;

public abstract class Mision {
    private String nombre;
    private int cantidadNecesaria;
    private String unidadDeMedida;

    public Insignia misionCumplida(Donante donante){
        Mision misionActual = donante.getMisionActual();
        if (misionActual.getProgresoActual(donante) == misionActual.cantidadNecesaria){
            return insignia = new Insignia (new Date(),self);
        }
        return null;
    }

    public int getCantidadNecesaria() {
        return cantidadNecesaria;
    }

    public abstract int getProgresoActual(Donante donante);
}
