package com.ddsi.donaciones.service;

import com.ddsi.donaciones.domain.GestorDonaciones;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SegmentacionDonacionesScheduler {
    @Scheduled(cron="0 0 0 * * *")
    public void segmentarDonaciones(){
        GestorDonaciones.getInstance().segmentarDonaciones();
    }
}
