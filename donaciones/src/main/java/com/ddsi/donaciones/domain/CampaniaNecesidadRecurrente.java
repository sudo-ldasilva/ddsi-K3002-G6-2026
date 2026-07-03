package com.ddsi.donaciones.domain;

import java.time.LocalDate;

public class CampaniaNecesidadRecurrente extends CampaniaNecesidad {
    private Periodo periodo;
    private LocalDate inicio;
    private LocalDate fechaFinal;
    public CampaniaNecesidadRecurrente(EntidadBeneficiaria entidadBeneficiaria, String descripcion, Periodo periodo) {
        super(entidadBeneficiaria, descripcion);
        this.periodo = periodo;
        this.inicio = LocalDate.now();
        asignarFinal(periodo);
    }
    public Periodo getPeriodo() {
        return periodo;
    }
    private void asignarFinal(Periodo periodo){
        switch(periodo){
            case ANUAL:
                this.fechaFinal = inicio.plusYears(1);
                break;
            case CUATRIMESTRAL:
                this.fechaFinal = inicio.plusMonths(4);
                break;
            case MENSUAL:
                this.fechaFinal= inicio.plusMonths(1);
                break;
            case SEMANAL:
                this.fechaFinal = inicio.plusWeeks(1);
                break;
        }
    }
    public void alargarCampaña() {
        switch (periodo) {
            case ANUAL:
                this.fechaFinal = fechaFinal.plusYears(1);
                break;
            case CUATRIMESTRAL:
                this.fechaFinal = fechaFinal.plusMonths(4);
                break;
            case MENSUAL:
                this.fechaFinal = fechaFinal.plusMonths(1);
                break;
            case SEMANAL:
                this.fechaFinal = fechaFinal.plusWeeks(1);
                break;
        }
    }
    public void resetearCampaña(){
        for (NecesidadIndividual necesidad : necesidades) {
            necesidad.reiniciar();
        }
        this.inicio = LocalDate.now();
        asignarFinal(periodo);
    }
}
