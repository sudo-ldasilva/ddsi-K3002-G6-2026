package com.ddsi.donaciones.domain;

import java.util.ArrayList;

public class GestorCategorias {
    private static GestorCategorias gestorCategorias = null;
    private ArrayList<Categoria> categorias;

    private GestorCategorias() {
        categorias = new ArrayList<>();
    }

    public static GestorCategorias getInstance() {
        if (gestorCategorias == null) gestorCategorias = new GestorCategorias();
        return gestorCategorias;
    }

    public void agregarCategoria(Categoria categoria) {
        if (!categorias.contains(categoria)) {
            categorias.add(categoria);
        }
    }

    public Categoria buscarCategoria(String categoriaNombre) {
        return categorias.stream().filter(c -> c.getNombre().equals(categoriaNombre)).findFirst().orElse(null);
    }
}
