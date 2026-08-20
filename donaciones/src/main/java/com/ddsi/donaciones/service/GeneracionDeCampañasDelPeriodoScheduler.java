package com.ddsi.donaciones.service;

import org.springframework.scheduling.annotation.Scheduled;

import com.ddsi.donaciones.domain.EntidadBeneficiaria;
import com.ddsi.donaciones.domain.GestorEntidadesBeneficiarias;

public class GeneracionDeCampañasDelPeriodoScheduler {

    private void crearCampañasDelPeriodoDeEntidadBeneficiaria(EntidadBeneficiaria entidad) {
        entidad.getCampañasRecurrentes().forEach(cr -> cr.crearSiguientePeriodoSiEsNecesario());
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void crearTodasLasCampañasDelPeriodo(){
        GestorEntidadesBeneficiarias.getInstance()
                                    .getEntidadesBeneficiarias()
                                    .forEach(e -> crearCampañasDelPeriodoDeEntidadBeneficiaria(e));
    }

}
