package com.ddsi.donaciones.domain;

public class Direccion {
    private String callePrincipal;
    private String calleEntre1;
    private String calleEntre2;
    private String altura;
    private String nroDepartamento;
    private Ciudad ciudad;

    public Direccion(String callePrincipal, String calleEntre1, String calleEntre2, String altura, String nroDepartamento, Ciudad ciudad) {
        this.callePrincipal = callePrincipal;
        this.calleEntre1 = calleEntre1;
        this.calleEntre2 = calleEntre2;
        this.altura = altura;
        this.nroDepartamento = nroDepartamento;
        this.ciudad = ciudad;
    }

    public String direccion(){
        String direccion = this.callePrincipal;

        if (altura != null)
            direccion += " " + this.altura;
        else
            direccion += " (Entre " + this.calleEntre1 + " y " + this.calleEntre2 + ")";

        if (nroDepartamento != null)
            direccion += " (Departamento " + nroDepartamento + ")";

        return direccion;
    }

    public String direccionCompleta(){
        return this.direccion() + ", " + this.ciudad.getUbicacionCompleta();
    }

}
