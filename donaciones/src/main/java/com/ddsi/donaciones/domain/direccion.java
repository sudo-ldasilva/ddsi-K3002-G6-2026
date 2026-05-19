package com.ddsi.donaciones.domain;

public class Direccion {
    private String callePrincipal;
    private String calleEntre1;
    private String calleEntre2;
    private String altura;
    private Ciudad ciudad;

    public Direccion(String callePrincipal, String calleEntre1, String calleEntre2, String altura, Ciudad ciudad) {
        this.callePrincipal = callePrincipal;
        this.calleEntre1 = calleEntre1;
        this.calleEntre2 = calleEntre2;
        this.altura = altura;
        this.ciudad = ciudad;
    }

    public String direccion(){
        if(altura != NULL)
            return this.callePrincipal + " " + this.altura;
        return this.callePrincipal + " (Entre " + this.calleEntre1 + " y " + this.calleEntre2 + ")";
    }

    public String direccionCompleta(){
        return this.direccion() + ", " + this.ciudad.getUbicacionCompleta();
    }

}