package com.ddsi.donaciones.domain;

import com.ddsi.donaciones.domain.dto.BienDTO;

public class Bien {
    private String descripcion;
    private String foto;
    private Subcategoria subcategoria;

    public Bien(BienDTO dto) {
        this(
            dto.getDescripcion(),
            dto.getFoto(),
            GestorCategorias.getInstance()
                            .getCategorias()
                            .stream()
                            .flatMap(c -> c.getSubcategorias().stream())
                            .filter(s -> s.getNombre().equals(dto.getSubcategoria().getNombre()))
                            .findFirst()
                            .orElse(null)
        );
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
