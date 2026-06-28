package com.ddsi.notificaciones.domain;

public class NotificadorEmail implements NotificadorStrategy{
    private EnvioEmailAdapter adapter;

    public NotificadorEmail(EnvioEmailAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void enviarMensaje(Notificacion notificacion){
        String direccion = notificacion.getDirecion();
        String mensaje = notificacion.getMensaje();
        adapter.enviarMail(String direccion, String mensaje);
    }

    @Override
    public TipoContacto getTipo(){
        return TipoContacto.EMAIL;
    }
}
