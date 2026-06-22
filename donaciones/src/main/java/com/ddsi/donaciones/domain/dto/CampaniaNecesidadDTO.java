package com.ddsi.donaciones.domain.dto;

import com.ddsi.donaciones.domain.NecesidadIndividual;

import java.util.ArrayList;
import java.util.UUID;

public class CampaniaNecesidadDTO {
    private UUID uuid;
    private String tipo;
    private ArrayList<NecesidadIndividual> necesidades;
    private String descripcion;
    private String periodo;
    private String situacionExcepcional;

    public CampaniaNecesidadDTO(UUID uuid, String tipo, ArrayList<NecesidadIndividual> necesidades, String descripcion, String periodo, String situacionExcepcional) {
        this.uuid = uuid;
        this.tipo = tipo;
        this.necesidades = necesidades;
        this.descripcion = descripcion;
        this.periodo = periodo;
        this.situacionExcepcional = situacionExcepcional;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTipo() {
        return tipo;
    }

    public ArrayList<NecesidadIndividual> getNecesidades() {
        return necesidades;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public String getSituacionExcepcional() {
        return situacionExcepcional;
    }
}
