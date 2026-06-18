package com.ddsi.notificaciones.dto;

public class NotificacionRequestDTO {
    private ArrayList<ContactoDTO> contactos;
    private String mensaje;

    public NotificacionRequestDTO() {}

    public NotificacionRequestDTO(List<ContactoDTO> contactos, String mensaje) {
        this.contactos = contactos;
        this.mensaje = mensaje;
    }

    public ArrayList<ContactoDTO> getContactos() { return contactos; }
    public String getMensaje() { return mensaje; }
}