package com.ddsi.incentivos.domain;

import com.ddsi.incentivos.domain.dto.DonacionIndependienteDTO;
import java.util.ArrayList;

public class MisionDonacionExitosa extends Mision {
    @Override
    public int getProgresoActual(Donante donante) {
        ArrayList<DonacionIndependienteDTO> donaciones = donante.getDonaciones();
        int recibidas = 0;
        for (int i = 0; i < donaciones.size(); i++){
            DonacionIndependienteDTO donacion = donaciones.get(i);
            if (donacion.getEstadoActual() == EstadoDonacion.ASIGNACION_REALIZADA) {
                recibidas++;
            }
        }
        return recibidas;
    }
}
