package com.ddsi.donaciones.service;

import com.ddsi.donaciones.domain.GestorEntidadesBeneficiarias;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ContadorDonacionesScheduler {

    @Scheduled(cron = "0 0 0 1 1,5,9 *")
    public void reiniciarContadoresDeDonaciones(){
        GestorEntidadesBeneficiarias.getInstance().reiniciarContadoresDeDonaciones();
    }
}
