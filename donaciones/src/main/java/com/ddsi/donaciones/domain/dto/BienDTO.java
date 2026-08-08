package com.ddsi.donaciones.domain.dto;

public class BienDTO {
    private String descripcion;
    private String foto;
    private SubcategoriaDTO subcategoria;

    public BienDTO(String descripcion, String foto, SubcategoriaDTO subcategoria) {
        this.descripcion = descripcion;
        this.foto = foto;
        this.subcategoria = subcategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public String getFoto() {
        return foto;
    }
    public SubcategoriaDTO getSubcategoria() {
        return subcategoria;
    }
}
