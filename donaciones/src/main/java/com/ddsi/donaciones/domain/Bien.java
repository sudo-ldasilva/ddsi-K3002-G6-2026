package com.ddsi.donaciones.domain;

import com.ddsi.donaciones.domain.dto.BienDTO;

public class Bien {
    private String descripcion;
    private String foto;
    private Subcategoria subcategoria;

    public Bien(BienDTO dto) {
        this(dto.getDescripcion(), dto.getFoto(), new Subcategoria(dto.getSubcategoria()));
    }

    public Bien(String descripcion, String foto, Subcategoria subcategoria) {
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
    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public BienDTO toDto() {
        return new BienDTO(
            descripcion,
            foto,
            subcategoria.toDto()
        );
    }
}
