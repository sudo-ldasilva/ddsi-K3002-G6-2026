package com.ddsi.incentivos.domain;

import java.util.ArrayList;
import com.ddsi.incentivos.domain.dto.DonacionIndependienteDTO;

public class MisionHabilDonador extends Mision {
    @Override
    public int getProgresoActual(Donante donante) {
        ArrayList<DonacionIndependienteDTO> donaciones = donante.getDonaciones();
        int max = 0;
        for (int i = 0; i<donaciones.size(); i++){
            DonacionIndependienteDTO donacion = donaciones.get(i);
            if(donacion.getCantidadBienes()>max){
                max = donacion.getCantidadBienes();
            }
        }
        return max;
    }
}
