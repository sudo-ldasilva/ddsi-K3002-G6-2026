package com.ddsi.notificaciones.domain;

public class GestorNotificaciones{
    private static GestorNotificaciones gestorNotificaciones = null;

    public static GestorNotificaciones getInstance() {
        if (gestorNotificaciones == null) {
            gestorNotificaciones = new GestorNotificaciones();
        }
        return gestorNotificaciones;
    }

    public void enviarMensaje(String mensaje, String tipoContacto, String direccion){
        //to do
    }
}