package com.ddsi.incentivos.domain;

import java.util.ArrayList;
import java.time.LocalDate;
import com.ddsi.incentivos.domain.dto.DonacionIndependienteDTO;

public class MisionRacha extends Mision {
    @Override
    public int getProgresoActual(Donante donante, ArrayList<DonacionIndependienteDTO> donaciones) {
        int progreso = 0;
        int mesActual = LocalDate.now().getMonthValue();
        for (int i = 0; i < this.getCantidadNecesaria(); i++){
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
            if(donacion.getFecha().getMonthValue()==mes) {
                return 1;
            }
        }
        return 0;
    }

}
