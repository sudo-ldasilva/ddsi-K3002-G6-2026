package com.ddsi.notificaciones.domain;

@Service
public class NotificadorSMS implements NotificadorStrategy {
    private EnvioSMSAdapter adapter;

    @Override
    public TipoContacto getTipo() {
        return TipoContacto.SMS;
    }
    @Override
    public void enviarMensaje(Notificacion notificacion) {
        adapter.enviarNotificacion(notificacion);
    }
}
