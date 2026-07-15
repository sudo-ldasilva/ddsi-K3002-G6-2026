package com.ddsi.donaciones.domain.dto;

import com.ddsi.donaciones.domain.NecesidadIndividual;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class CampaniaNecesidadDTO {
    private UUID uuid;
    private String tipo;
    private ArrayList<NecesidadIndividual> necesidades;
    private String descripcion;
    private LocalDate fechaInicio;
    private String situacionExcepcional;

    public CampaniaNecesidadDTO(UUID uuid, String tipo, ArrayList<NecesidadIndividual> necesidades, String descripcion, LocalDate fechaInicio, String situacionExcepcional) {
        this.uuid = uuid;
        this.tipo = tipo;
        this.necesidades = necesidades;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public String getSituacionExcepcional() {
        return situacionExcepcional;
    }
}
