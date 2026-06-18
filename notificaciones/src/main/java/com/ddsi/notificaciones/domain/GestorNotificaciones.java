package com.ddsi.notificaciones.domain;

public class GestorNotificaciones{
    private static GestorNotificaciones gestorNotificaciones = null;

    public static GestorNotificaciones getInstance() {
        if (gestorNotificaciones == null) {
            gestorNotificaciones = new GestorNotificaciones();
        }
        return gestorNotificaciones;
    }

    public void enviarMensaje(String mensaje, String tipoContacto, String direccion) {
        return switch (tipoContacto.toUpperCase()) {
            case "MAIL" -> new ContactoMail(mensaje,direccion);
            case "TELEFONO", "SMS" -> new ContactoTelefono(mensaje, direccion);
            case "WHATSAPP" -> new ContactoWhatsapp(mensaje, direccion);
            case "TELEGRAM" -> new ContactoTelegram(mensaje, direccion, direccion);
            default -> return false;
        };
    }
}