package com.ddsi.incentivos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ddsi.incentivos.domain.*;
import com.ddsi.incentivos.domain.dto.DonacionIndependienteDTO;
import com.ddsi.incentivos.domain.dto.DonacionesPorMailDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@SpringBootTest
class IncentivosApplicationTests {

	//El test corre simulando tener donaciones sin conectar con donaciones
	@Test
	void testActualizarProgreso() {
		ArrayList<Mision> misionesACompletar = new ArrayList<Mision>();
		misionesACompletar.add(new MisionRacha("Racha 1", 1, "Dia"));
		misionesACompletar.add(new MisionCompletitud("Completitud 1", 1, "Categoria"));
        GestorIncentivos.getInstance().agregarDonante(new Donante("pepito@gmail.com",
														new Categoria("Colaborador", misionesACompletar,
																new Categoria("Sostenedor", misionesACompletar, null)),
														0, new ArrayList<>(), true));
		ArrayList<DonacionesPorMailDTO> donaciones = new ArrayList<>();
		ArrayList<DonacionIndependienteDTO> donacionesIndependiente = new ArrayList<>();
		donacionesIndependiente.add(new DonacionIndependienteDTO(UUID.randomUUID(), "Mueble", "pepito@gmail.com", 2, EstadoDonacion.ASIGNACION_REALIZADA, LocalDate.now()));
		donaciones.add(new DonacionesPorMailDTO("pepito@gmail.com", donacionesIndependiente));
		GestorIncentivos.getInstance().actualizarProgreso(donaciones);

		assertEquals("Sostenedor", GestorIncentivos.getInstance().getDonantes().getFirst().getCategoriaActual().getNombre());
	}

}
