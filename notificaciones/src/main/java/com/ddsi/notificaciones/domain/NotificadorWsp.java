package com.ddsi.notificaciones.domain;

public class NotificadorWsp implements NotificadorStrategy {
    private EnvioWhatsAppAdapter adapter;

    @Override
    public TipoContacto getTipo() {
        return TipoContacto.SMS;
    }
    @Override
    public void enviarMensaje(Notificacion notificacion) {
        String direccion = notificacion.getDirecion();
        String mensaje = notificacion.getMensaje();
        adapter.enviarWsp(String direccion, String mensaje);
    }
}
