package com.ddsi.donaciones.domain.dto;

public class DestinatarioNotificacionDTO {
    private String nombreIdentificatorio;
    private String tipoContacto;
    private String direccionContacto;

    public DestinatarioNotificacionDTO(String nombreIdentificatorio, String tipoContacto, String direccionContacto) {
        this.nombreIdentificatorio = nombreIdentificatorio;
        this.tipoContacto = tipoContacto;
        this.direccionContacto = direccionContacto;
    }

    public String getNombreIdentificatorio() {
        return nombreIdentificatorio;
    }

    public String getTipoContacto() {
        return tipoContacto;
    }

    public String getDireccionContacto() {
        return direccionContacto;
    }
}
