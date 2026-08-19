package com.ddsi.donaciones.domain.dto;

import com.ddsi.donaciones.domain.EstadoDonacion;
import com.ddsi.donaciones.domain.ComprobanteRecepcion;

import java.time.LocalDate;
import java.util.UUID;

public class DonacionIndependienteDTO {
    private UUID uuid;
    private String subcategoria;
    private String mailDonante;
    private int cantidadBienes;
    private EstadoDonacion estadoActual;
    private LocalDate fecha;
    private ComprobanteRecepcion comprobante;

    public DonacionIndependienteDTO(UUID uuid, String categoriaBien, String mailDonante, int cantidadBienes, EstadoDonacion estadoActual, LocalDate fecha, ComprobanteRecepcion comprobante) {
        this.uuid = uuid;
        this.subcategoria = categoriaBien;
        this.mailDonante = mailDonante;
        this.cantidadBienes = cantidadBienes;
        this.estadoActual = estadoActual;
        this.fecha = fecha;
        this.comprobante = comprobante;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public String getMailDonante() {
        return mailDonante;
    }

    public int getCantidadBienes() {
        return cantidadBienes;
    }

    public EstadoDonacion getEstadoActual() {
        return estadoActual;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public ComprobanteRecepcion getComprobante() {
        return comprobante;
    }
}
