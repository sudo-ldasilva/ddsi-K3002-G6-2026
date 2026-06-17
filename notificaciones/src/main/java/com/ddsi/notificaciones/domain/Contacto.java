package com.ddsi.notificaciones.domain;

import java.util.List;

public interface Contacto {
    public void enviarMensaje(String mensaje, String direccion);
    //dejamos la implementación para entrega futura, vamos a usar polimorfismo
}
