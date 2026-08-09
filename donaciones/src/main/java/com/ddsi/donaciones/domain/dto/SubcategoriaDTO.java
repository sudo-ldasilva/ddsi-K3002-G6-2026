package com.ddsi.donaciones.domain.dto;

public class SubcategoriaDTO {
    public String nombre;
    public String unidadDeConteo;

    public SubcategoriaDTO(String nombre, String unidadDeConteo, String categoria) {
        this.nombre = nombre;
        this.unidadDeConteo = unidadDeConteo;
    }

    public String getNombre() {
        return nombre;
    }
    public String getUnidadDeConteo() {
        return unidadDeConteo;
    }
}
