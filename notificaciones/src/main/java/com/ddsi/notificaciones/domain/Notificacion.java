package com.ddsi.notificaciones.domain;

public class Notificacion {
    private String mensaje;
    private String direcion;
    private TipoContacto tipoContacto;
    private Boolean estaCompletada;

    public Notificacion(String direcion, String mensaje, TipoContacto tipoContacto, Boolean estaCompletada) {
        this.direcion = direcion;
        this.mensaje = mensaje;
        this.tipoContacto = tipoContacto;
        this.estaCompletada = estaCompletada;
    }

    public String getDirecion() {
        return direcion;
    }
    public String getMensaje() {
        return mensaje;
    }
    public TipoContacto getTipoContacto() {
        return tipoContacto;
    }
    public boolean setEstaCompletada(boolean valor) {
        return estaCompletada = valor;
    }
}
