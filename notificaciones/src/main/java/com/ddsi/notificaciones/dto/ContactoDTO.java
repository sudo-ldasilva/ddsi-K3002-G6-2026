package com.ddsi.notificaciones.dto;

public class ContactoDTO {
    private String tipoContacto;
    private String direccion;

    public ContactoDTO() {}

    public ContactoDTO(String tipoContacto, String direccion) {
        this.tipoContacto = tipoContacto;
        this.direccion = direccion;
    }

    public String getTipoContacto() { return tipoContacto; }
    public String getDireccion() { return direccion; }
}