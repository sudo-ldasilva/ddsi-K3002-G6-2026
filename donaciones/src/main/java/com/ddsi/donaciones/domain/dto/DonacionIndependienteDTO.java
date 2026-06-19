package com.ddsi.donaciones.domain.dto;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

public class DonacionIndependienteDTO {
    private UUID uuid;
    private String categoriaBien;
    private String mailDonante;
    private int cantidadBienes;
    private int estadoActual;
    private Date fecha;

    public DonacionIndependienteDTO(UUID uuid, String categoriaBien, String mailDonante, int cantidadBienes, int estadoActual, Date fecha) {
        this.uuid = uuid;
        this.categoriaBien = categoriaBien;
        this.mailDonante = mailDonante;
        this.cantidadBienes = cantidadBienes;
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

    public int getCantidadBienes() {
        return cantidadBienes;
    }

    public void setCantidadBienes(int cantidadBienes) {
        this.cantidadBienes = cantidadBienes;
    }

    public int getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(int estadoActual) {
        this.estadoActual = estadoActual;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
