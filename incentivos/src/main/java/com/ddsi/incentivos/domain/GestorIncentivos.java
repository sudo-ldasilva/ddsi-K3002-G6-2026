package com.ddsi.incentivos.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.ddsi.donaciones.service.NotificacionDispatcherService;

public class GestorIncentivos {
    private static GestorIncentivos gestorIncentivos = null;
    private ArrayList<Donante> donantes;
    private ArrayList<Categoria> categorias;

    private GestorIncentivos() {
        this.donantes = new ArrayList<>();
        this.categorias = new ArrayList<>();
    }

    private static GestorIncentivos getInstance() {
        if (gestorIncentivos == null) {
            gestorIncentivos = new GestorIncentivos();
        }
        return gestorIncentivos;
    }


}
