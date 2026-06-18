package com.ddsi.notificaciones.domain;

import com.ddsi.notificaciones.dto.ContactoDTO;

public class GestorNotificaciones {

    private static GestorNotificaciones gestorNotificaciones = null;

    private GestorNotificaciones() {}

    public static GestorNotificaciones getInstance() {
        if (gestorNotificaciones == null) {
            gestorNotificaciones = new GestorNotificaciones();
        }
        return gestorNotificaciones;
    }

    public boolean enviarMensaje(ContactoDTO contacto, String mensaje) {
        try {
            switch (contacto.getTipoContacto().toUpperCase()) {
                case "MAIL"            -> new ContactoMail().enviarMensaje(mensaje, contacto.getDireccion());
                case "SMS" -> new ContactoSMS().enviarMensaje(mensaje, contacto.getDireccion());
                case "WHATSAPP"        -> new ContactoWhatsapp().enviarMensaje(mensaje, contacto.getDireccion());
                default                -> { return false; }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}