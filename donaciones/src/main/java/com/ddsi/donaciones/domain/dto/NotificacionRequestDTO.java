package com.ddsi.donaciones.domain.dto;

public class NotificacionRequestDTO {
    private String tipoContacto;
    private String direccionContacto;
    private String mensaje;

    public NotificacionRequestDTO() {
    }

    public NotificacionRequestDTO(String tipoContacto, String direccionContacto, String mensaje) {
        this.tipoContacto = tipoContacto;
        this.direccionContacto = direccionContacto;
        this.mensaje = mensaje;
    }

    public String getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(String tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    public String getDireccionContacto() {
        return direccionContacto;
    }

    public void setDireccionContacto(String direccionContacto) {
        this.direccionContacto = direccionContacto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
