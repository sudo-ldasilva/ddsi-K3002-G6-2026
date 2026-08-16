package com.ddsi.donaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// import java.util.ArrayList;
// import com.ddsi.donaciones.domain.Contacto;
// import com.ddsi.donaciones.service.NotificacionDispatcherService;

@SpringBootApplication
@EnableScheduling
public class DonacionesApplication {

    // private static void molestarAAnto() {
    //     NotificacionDispatcherService notif = new NotificacionDispatcherService();
    //     ArrayList<Contacto> cs = new ArrayList<>();
    //     cs.add(new Contacto("+5491168603322", "SMS"));
    //     notif.notificar(
    //         cs,
    //         "Hola Anto, estamos ejecutando el servicio de Notificaciones!"
    //     );
    // }

    // private static void molestarALeo() {
    //     NotificacionDispatcherService notif = new NotificacionDispatcherService();
    //     ArrayList<Contacto> cs = new ArrayList<>();
    //     cs.add(new Contacto("ldasilva@frba.utn.edu.ar", "mail"));
    //     notif.notificar(
    //         cs,
    //         "Bueeeeenas"
    //     );
    // }

	public static void main(String[] args) {
		System.out.println("Hola, mundo!");
        // molestarAAnto();
        // molestarALeo();
		SpringApplication.run(DonacionesApplication.class, args);
	}
}
