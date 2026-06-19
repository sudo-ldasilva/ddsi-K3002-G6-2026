package com.ddsi.incentivos.domain;

import java.util.ArrayList;
import com.ddsi.incentivos.domain.dto.DonacionIndependienteDTO;

public class MisionCompletitud extends Mision {
    @Override
    public int getProgresoActual(Donante donante) {
        ArrayList<DonacionIndependienteDTO> donaciones = donante.getDonaciones();
        ArrayList<String> categorias = new ArrayList<>();
        for(int i = 0; i < donaciones.size();i++){
            DonacionIndependienteDTO donacion = donaciones.get(i);
            if(!categorias.contains(donacion.getCategoriaBien())){
                categorias.add(donacion.getCategoriaBien());
            }
        }
        return categorias.size();
    }
}
