package com.ddsi.donaciones;

import java.util.ArrayList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ddsi.donaciones.domain.Contacto;
import com.ddsi.donaciones.service.NotificacionDispatcherService;

@SpringBootApplication
@EnableScheduling
public class DonacionesApplication {

    private void molestarAAnto() {
        NotificacionDispatcherService notif = new NotificacionDispatcherService();
        ArrayList<Contacto> cs = new ArrayList<>();
        cs.add(new Contacto("+5491168603322", "SMS"));
        notif.notificar(
            cs,
            "Hola"
        );
    }

	public static void main(String[] args) {
		System.out.println("Hola, mundo!");
		SpringApplication.run(DonacionesApplication.class, args);
	}
}
