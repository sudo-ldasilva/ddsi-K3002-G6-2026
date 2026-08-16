package com.ddsi.incentivos.domain.dto;

import java.time.LocalDate;
import java.util.UUID;
import com.ddsi.incentivos.domain.EstadoDonacion;

public class DonacionIndependienteDTO {
    private UUID uuid;
    private String categoriaBien;
    private String mailDonante;
    private int cantidadBienes;
    private EstadoDonacion estadoActual;
    private LocalDate fecha;

    public DonacionIndependienteDTO(UUID uuid, String categoriaBien, String mailDonante, int cantidadBienes, EstadoDonacion estadoActual, LocalDate fecha) {
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

    public EstadoDonacion getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(EstadoDonacion estadoActual) {
        this.estadoActual = estadoActual;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
