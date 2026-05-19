package com.ddsi.donaciones.domain;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Bien {
    private String descripcion;
    private Path foto;
    private Subcategoria subcategoria;

    public Bien(String descripcion, String foto, Subcategoria subcategoria) {
        this.descripcion = descripcion;
        this.foto = Paths.get(foto);
        this.subcategoria = subcategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public Path getFoto() {
        return foto;
    }
    public Subcategoria getSubcategoria() {
        return subcategoria;
    }
}
