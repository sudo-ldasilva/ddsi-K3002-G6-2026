package com.ddsi.incentivos.domain.dto;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

public class DonacionIndependienteDTO {
    private UUID uuid;
    private String categoriaBien;
    private String mailDonante;
    private ArrayList<String> bienes;
    private String estadoActual;
    private Date fecha;

    public DonacionIndependienteDTO(UUID uuid, String categoriaBien, String mailDonante, ArrayList<String> bienes, String estadoActual, Date fecha) {
        this.uuid = uuid;
        this.categoriaBien = categoriaBien;
        this.mailDonante = mailDonante;
        this.bienes = bienes;
        this.estadoActual = estadoActual;
        this.fecha = fecha;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getCategoriaBien() {
        return categoriaBien;
    }

    public void setCategoriaBien(String categoriaBien) {
        this.categoriaBien = categoriaBien;
    }

    public String getMailDonante() {
        return mailDonante;
    }

    public void setMailDonante(String mailDonante) {
        this.mailDonante = mailDonante;
    }

    public ArrayList<String> getBienes() {
        return bienes;
    }

    public void setBienes(ArrayList<String> bienes) {
        this.bienes = bienes;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
