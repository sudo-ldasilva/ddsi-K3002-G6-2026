package com.ddsi.donaciones.domain;

import com.ddsi.donaciones.domain.dto.SubcategoriaDTO;

public class Subcategoria {
    public String nombre;
    public String unidadDeConteo;
    public Categoria categoria;

    public Subcategoria(SubcategoriaDTO dto) {
        this(dto.getNombre(), dto.getUnidadDeConteo(), GestorCategorias.getInstance().buscarCategoria(dto.getCategoria()));
    }

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

    public SubcategoriaDTO toDto() {
        return new SubcategoriaDTO(
            nombre,
            unidadDeConteo,
            categoria.getNombre()
        );
    }
}
