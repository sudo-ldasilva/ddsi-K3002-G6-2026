package com.ddsi.incentivos.services;

import com.ddsi.incentivos.domain.GestorIncentivos;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ActualizarProgresoScheduler {

    @Scheduled(cron = "0 0 0 * * *")
    public void actualizarProgreso(){
        GestorIncentivos.getInstance().actualizarProgreso();
    }
}
