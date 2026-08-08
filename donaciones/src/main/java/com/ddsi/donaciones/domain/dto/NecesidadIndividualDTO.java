package com.ddsi.donaciones.domain.dto;

import java.util.ArrayList;
import java.util.UUID;

public class NecesidadIndividualDTO {
    private UUID uuid;
    private BienDTO bien;//que se necesita,ej)sillas,ropa,comida
    private int cantidadNecesaria;//cuanto se necesita
    private ArrayList<DonacionIndependienteDTO> donaciones;//las donaciones independientes que se van juntando para satisfacer
    private UUID campaniaUUID;

    public NecesidadIndividualDTO() {}

    public NecesidadIndividualDTO(UUID uuid, BienDTO bien, int cantidadNecesaria, UUID campaniaUUID) {
        this.uuid = uuid;
        this.bien = bien;
        this.cantidadNecesaria = cantidadNecesaria;
        this.campaniaUUID = campaniaUUID;
        this.donaciones = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    };
    public BienDTO getBien() {
        return bien;
    }
    public int getCantidadNecesaria() {
        return cantidadNecesaria;
    }
    public ArrayList<DonacionIndependienteDTO> getDonaciones() {
        return donaciones;
    }
    public UUID getCampaniaUUID() {
        return campaniaUUID;
    }
}
