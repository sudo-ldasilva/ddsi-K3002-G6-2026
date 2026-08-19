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

	//El test corre simulando tener donaciones sin conectar con donaciones
	@Test
	void testGenerarRanking(){
		ArrayList<Mision> misionesACompletar = new ArrayList<Mision>();
		misionesACompletar.add(new MisionRacha("Racha 1", 1, "Dia"));
		misionesACompletar.add(new MisionCompletitud("Completitud 2", 2, "Categoria"));
		GestorIncentivos.getInstance().agregarDonante(new Donante("pepito@gmail.com",
				new Categoria("Colaborador", misionesACompletar,
						new Categoria("Sostenedor", misionesACompletar, null)),
				0, new ArrayList<>(), true));
		GestorIncentivos.getInstance().agregarDonante(new Donante("fulana@gmail.com",
				new Categoria("Colaborador", misionesACompletar,
						new Categoria("Sostenedor", misionesACompletar, null)),
				0, new ArrayList<>(), true));

		ArrayList<DonacionesPorMailDTO> donaciones = new ArrayList<>();
		ArrayList<DonacionIndependienteDTO> donacionesIndependiente1 = new ArrayList<>();
		donacionesIndependiente1.add(new DonacionIndependienteDTO(UUID.randomUUID(), "Mueble", "pepito@gmail.com", 1, EstadoDonacion.ASIGNACION_REALIZADA, LocalDate.now()));
		donaciones.add(new DonacionesPorMailDTO("pepito@gmail.com", donacionesIndependiente1));

		ArrayList<DonacionIndependienteDTO> donacionesIndependiente2 = new ArrayList<>();
		donacionesIndependiente2.add(new DonacionIndependienteDTO(UUID.randomUUID(), "Mueble", "fulana@gmail.com", 2, EstadoDonacion.ASIGNACION_REALIZADA, LocalDate.now()));
		donacionesIndependiente2.add(new DonacionIndependienteDTO(UUID.randomUUID(), "Comida", "fulana@gmail.com", 3,  EstadoDonacion.ASIGNACION_REALIZADA, LocalDate.now()));
		donaciones.add(new DonacionesPorMailDTO("fulana@gmail.com", donacionesIndependiente2));

		GestorIncentivos.getInstance().actualizarProgreso(donaciones);

		ArrayList<Donante> ranking = GestorIncentivos.getInstance().rankingMensual();
		assertEquals("fulana@gmail.com", ranking.get(0).getMail());
		assertEquals("pepito@gmail.com",  ranking.get(1).getMail());
	}
}
