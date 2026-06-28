package com.ddsi.notificaciones.domain;

public class NotificadorSMS implements NotificadorStrategy {
    private EnvioSMSAdapter adapter;

    @Override
    public TipoContacto getTipo() {
        return TipoContacto.SMS;
    }
    @Override
    public void enviarMensaje(Notificacion notificacion) {
        String direccion = notificacion.getDirecion();
        String mensaje = notificacion.getMensaje();
        adapter.enviarSMS(String direccion, String mensaje);
    }
}
