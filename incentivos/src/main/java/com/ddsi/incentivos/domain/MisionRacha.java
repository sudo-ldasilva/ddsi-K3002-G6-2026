package com.ddsi.incentivos.domain;

import java.util.ArrayList;
import java.util.Date;
import com.ddsi.incentivos.domain.dto.DonacionIndependienteDTO;

public class MisionRacha extends Mision {
    @Override
    public int getProgresoActual(Donante donante) {
        int progreso = 0;
        ArrayList<DonacionIndependienteDTO> donaciones = donante.getDonaciones();
        java.util.Date fecha = new Date();
        int mesActual = fecha.getMonth();
        for (int i = 0; i <this.getCantidadNecesaria(); i++){
            int a = this.esteMes(donaciones, mesActual);
            if (a == 0) {
                return progreso;
            }
            progreso += a;
            mesActual--;
            if (mesActual == -1) {
                mesActual = 11;
            }
        }
        return progreso;
    }

    int esteMes(ArrayList<DonacionIndependienteDTO> donaciones, int mes){
        for (int i = 0; i<donaciones.size(); i++){
            DonacionIndependienteDTO donacion = donaciones.get(i);
            if(donacion.getFecha().getMonth()==mes) {
                return 1;
            }
        }
        return 0;
    }

}
