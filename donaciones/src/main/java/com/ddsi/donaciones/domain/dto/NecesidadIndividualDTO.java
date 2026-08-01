package com.ddsi.donaciones.domain.dto;

import com.ddsi.donaciones.domain.*;
import java.util.ArrayList;
import java.util.UUID;
import java.util.function.ToIntFunction;

public class NecesidadIndividualDTO {
    private UUID uuid;
    private Bien bien;//que se necesita,ej)sillas,ropa,comida
    private int cantidadNecesaria;//cuanto se necesita
    private ArrayList<DonacionIndependienteDTO> donaciones;//las donaciones independientes que se van juntando para satisfacer
    private CampaniaNecesidadDTO campania;

    public NecesidadIndividualDTO() {}

    public NecesidadIndividualDTO(UUID uuid, Bien bien, int cantidadNecesaria, CampaniaNecesidadDTO campania) {
        this.uuid = uuid;
        this.bien = bien;
        this.cantidadNecesaria = cantidadNecesaria;
        this.campania = campania;
        this.donaciones = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    };
    public Bien getBien() {
        return bien;
    }
    public int getCantidadNecesaria() {
        return cantidadNecesaria;
    }
    public ArrayList<DonacionIndependienteDTO> getDonaciones() {
        return donaciones;
    }
    public CampaniaNecesidadDTO getCampania() {
        return campania;
    }
}
