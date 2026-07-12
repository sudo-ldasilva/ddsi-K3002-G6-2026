package com.ddsi.donaciones.service;

import com.ddsi.donaciones.domain.GestorDonantes;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class InactividadScheduler {

    @Scheduled(cron = "0 0 0 * * *")
    public void inactividad(){
    }
}
