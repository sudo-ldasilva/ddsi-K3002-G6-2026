package com.ddsi.donaciones.domain;

public class ContactoCelular extends Contacto {
    private boolean tieneWhatsApp;

    public ContactoCelular(String direccion, boolean tieneWhatsApp) {
        super(direccion);
        this.tieneWhatsApp = tieneWhatsApp;
    }

    public void setTieneWhatsApp(boolean tieneWhatsApp) {
        // Tiene sentido que pueda cambiar esto, no?
        this.tieneWhatsApp = tieneWhatsApp;
    }

    public boolean tieneWhatsApp() {
        return tieneWhatsApp;
    }
}
