package com.ddsi.donaciones.domain;
//Vamos a mandar el string completo, de ahi se separa el codigo de area y si es fijo
public class ContactoTelefono implements Contacto {
    private String numero;
    private String codigoDeArea;
    private boolean esFijo;

    public ContactoTelefono(String numeroCompleto) {
        String[] partesDelNumero = numeroCompleto.split(" ",2);
        this.codigoDeArea = partesDelNumero[0];
        this.numero = partesDelNumero[1];
        if(this.numero.startsWith("11")){
            this.esFijo = false;
        }
        else{ this.esFijo = true; }
    }

    public void MandarMensaje(String mensaje) {
        // TODO tercerizado
    }
}
