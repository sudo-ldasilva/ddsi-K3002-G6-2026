package com.ddsi.donaciones.service;

import com.ddsi.donaciones.domain.GestorEntidadesBeneficiarias;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CambioEstadoScheduler {

    @Scheduled(cron = "0 0 0 * * *")
    public void reiniciarContadoresDeDonaciones(){
        GestorEntidadesBeneficiarias.getInstance().reiniciarContadoresDeDonaciones();
    }
}
