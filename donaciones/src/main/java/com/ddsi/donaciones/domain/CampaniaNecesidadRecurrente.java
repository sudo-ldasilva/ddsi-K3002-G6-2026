package com.ddsi.donaciones.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CampaniaNecesidadRecurrente {
    private Periodo periodo;
    private List<CampaniaNecesidadPeriodo> campanias;
    private String descripcion;
    private Boolean activo;
    private ArrayList<NecesidadIndividual> necesidadesBase;
    private EntidadBeneficiaria entidadBeneficiaria;

    public CampaniaNecesidadRecurrente(Periodo periodo, String descripcion, Boolean activo, ArrayList<NecesidadIndividual> necesidadesBase, EntidadBeneficiaria entidadBeneficiaria) {
        this.periodo = periodo;
        this.campanias = new ArrayList<>();
        this.descripcion = descripcion;
        this.activo = activo;
        this.necesidadesBase = necesidadesBase;
        this.entidadBeneficiaria = entidadBeneficiaria;
    }

    public Periodo getPeriodo() {
        return periodo;
    }
    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public List<CampaniaNecesidadPeriodo> getCampanias() {
        return campanias;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void agregarCampania(CampaniaNecesidadPeriodo campania) {
        this.campanias.add(campania);
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public void crearSiguientePeriodo(){
        if(!activo && this.seCumplioUltimoPeriodo()) return;


        CampaniaNecesidadPeriodo nuevaCampania = new CampaniaNecesidadPeriodo(entidadBeneficiaria, descripcion, this.siguienteFechaInicio(), this, necesidadesBase);
        this.agregarCampania(nuevaCampania);
    }

    private LocalDate siguienteFechaInicio(){
        CampaniaNecesidadPeriodo ultimaCampania = campanias.get(campanias.size()-1);

        LocalDate fechaNueva;

        switch(periodo){
            case ANUAL ->  fechaNueva = ultimaCampania.getFechaInicio().plusYears(1);
            case CUATRIMESTRAL -> fechaNueva = ultimaCampania.getFechaInicio().plusMonths(4);
            case MENSUAL -> fechaNueva = ultimaCampania.getFechaInicio().plusMonths(1);
            case SEMANAL ->  fechaNueva = ultimaCampania.getFechaInicio().plusWeeks(1);
            default ->  fechaNueva = ultimaCampania.getFechaInicio();
        }

        return fechaNueva;
    }

    private boolean seCumplioUltimoPeriodo(){
        return this.siguienteFechaInicio().isBefore(LocalDate.now());
    }


    /*
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
     */
}
