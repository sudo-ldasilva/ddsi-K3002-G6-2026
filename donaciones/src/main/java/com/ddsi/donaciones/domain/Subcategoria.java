package com.ddsi.donaciones.domain;

public class Subcategoria {
    public String nombre;
    public String unidadDeConteo;
    public Categoria categoria;

    public Subcategoria(String nombre, String unidadDeConteo, Categoria categoria) {
        this.nombre = nombre;
        this.unidadDeConteo = unidadDeConteo;
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public String getNombre() {
        return nombre;
    }
    public String getUnidadDeConteo() {
        return unidadDeConteo;
    }
}
