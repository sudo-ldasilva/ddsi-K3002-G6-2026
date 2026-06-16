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

    //GETTERS
    public String getCallePrincipal() {
        return callePrincipal;
    }

    public String getCalleEntre1() {
        return calleEntre1;
    }

    public String getCalleEntre2() {
        return calleEntre2;
    }

    public String getAltura() {
        return altura;
    }

    public String getNroDepartamento() {
        return nroDepartamento;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    //SETTERS
    public void setCallePrincipal(String callePrincipal) {
        this.callePrincipal = callePrincipal;
    }

    public void setCalleEntre1(String calleEntre1) {
        this.calleEntre1 = calleEntre1;
    }

    public void setCalleEntre2(String calleEntre2) {
        this.calleEntre2 = calleEntre2;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public void setNroDepartamento(String nroDepartamento) {
        this.nroDepartamento = nroDepartamento;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    //OTROS
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
