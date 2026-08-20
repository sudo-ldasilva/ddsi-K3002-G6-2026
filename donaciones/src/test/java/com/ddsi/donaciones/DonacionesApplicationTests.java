package com.ddsi.donaciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;

import com.ddsi.donaciones.domain.*;

@SpringBootTest
class DonacionesApplicationTests {

    public PersonaHumana donantePostCarga = new PersonaHumana(
        "agustinaálvarez4201@contacto.ar",
        new Documento(TipoDocumento.DNI, "35489439"),
        "Agustina Álvarez",
        89,
        Genero.NOBINARIO,
        new Direccion(
            "Sarasa",
            "Chamuyo",
            "Verso",
            "-5000",
            "4B",
            new Ciudad(
                "CABA",
                new Provincia(
                    "CABA",
                    new Pais(
                        "Argentina"
                    )
                )
            )
        ),
        new Contacto("+54 11 4491-1851", "mail")
    );

    public PersonaHumana donantePrevioACarga = new PersonaHumana(
        "agustinaálvarez4201@contacto.ar",
        new Documento(TipoDocumento.LIBRETA_CIVICA, "123456"),
        "Roberto Gonzalez Del Monte Carlo",
        89,
        Genero.NOBINARIO,
        new Direccion(
            "Sarasa",
            "Chamuyo",
            "Verso",
            "-5000",
            "4B",
            new Ciudad(
                "CABA",
                new Provincia(
                    "CABA",
                    new Pais(
                        "Argentina"
                    )
                )
            )
        ),
        new Contacto("+5495353456", "mail")
    );
    int donantePosicionCSV = 4621;

    EntidadBeneficiaria pepito = new EntidadBeneficiaria(
        "Pepito S.R.L.",
        "algo",
        "11 11112222",
        new Direccion(
            "Saraza",
            "Vegetta",
            "Willyrex",
            "777",
            "S06",
            new Ciudad(
                "CABA",
                new Provincia(
                    "CABA",
                    new Pais("Argentina")
                )
            )
        )
    );

    EntidadBeneficiaria pepita = new EntidadBeneficiaria(
        "Pepita S.R.L.",
        "alga",
        "11 11112223",
        new Direccion(
            "Saraza",
            "Vegetta",
            "Willyrexa",
            "777",
            "S06",
            new Ciudad(
                "CABA",
                new Provincia(
                    "CABA",
                    new Pais("Argentina")
                )
            )
        )
    );

    Donante donante = new PersonaHumana(
        "stonko@mail.com",
        new Documento(
            TipoDocumento.DNI,
            "0606456"
        ),
        "Luciano Stonko",
        102,
        Genero.NOBINARIO,
        new Direccion(
            "Sarasa",
            "Chamuyo",
            "Verso",
            "5000",
            "4B",
            new Ciudad(
                "CABA",
                new Provincia(
                    "CABA",
                    new Pais(
                        "Argentina"
                    )
                )
            )
        ),
        new Contacto(
            "549155548487",
            "telefono"
        )
    );
    Subcategoria mesa = new Subcategoria("Mesa", "unidad", new Categoria("Mueble", new ArrayList<>(), false, false));
    Bien mesaBien = new Bien("Mesa xd", "", mesa);
    Donacion donacion = new Donacion(
        new Direccion(
            "Ohm",
            "Ampere",
            "Volt",
            "2500",
            "4B",
            new Ciudad(
                "CABA",
                new Provincia(
                    "CABA",
                    new Pais(
                        "Argentina"
                    )
                )
            )
        ),
        donante,
        "Tocá madera",
        new ArrayList<BienDonado>(Arrays.asList(new BienDonado(10, mesaBien))),
        LocalDate.now()
    );

    NecesidadIndividual necesidadPepita = new NecesidadIndividual(mesaBien, 10, null);
    CampaniaNecesidadExtraordinaria campañaPepita = new CampaniaNecesidadExtraordinaria(pepita, "ayuda", new ArrayList<>(Arrays.asList(necesidadPepita)), "Tengo hambre", LocalDate.of(2026, 8, 20));
    NecesidadIndividual necesidadPepito = new NecesidadIndividual(mesaBien, 10, null);
    CampaniaNecesidadExtraordinaria campañaPepito = new CampaniaNecesidadExtraordinaria(pepito, "ayuda", new ArrayList<>(Arrays.asList(necesidadPepito)), "Tengo sueño", LocalDate.of(2026, 8, 10));

    @BeforeEach
    public void limpiarDonantes() {
        GestorDonantes.getInstance().dropDonantes();
        GestorDonaciones.getInstance().dropDonaciones();
    }

    @Test
    public void cargarDonanteTemplate() {
        GestorDonantes.getInstance().agregarDonante(donantePrevioACarga);

        ArrayList<Donante> ds = new ArrayList<>();
        ds.add(donantePrevioACarga);
        assertEquals(GestorDonantes.getInstance().getDonantes(), ds);
    }

    public void compararDonantePostCarga(Donante d) {
        assertInstanceOf(PersonaHumana.class, d);
        assertEquals(donantePostCarga.getDocumento().getDocumento(), d.getDocumento().getDocumento());
        assertEquals(donantePostCarga.getDocumento().getTipoDocumento(), d.getDocumento().getTipoDocumento());
        assertEquals(donantePostCarga.getNombreYApellido(), ((PersonaHumana)d).getNombreYApellido());
        assertEquals(donantePostCarga.getMail(), d.getMail());
        assertTrue(d.tieneContacto(donantePostCarga.getMediosDeContacto().get(0)));
    }
    //
    // @Test
    // void cargaCSV() throws Exception {
    //     cargarDonanteTemplate();
    //
    //     File path = new File("../donantes_import_20000_UTF8_BOM.csv");
    //     CargaDeDatosDesdeCSV carga = new CargaDeDatosDesdeCSV();
    //
    //     ArrayList<Donante> donantes = carga.cargarDonantes(GestorDonantes.getInstance().getDonantes(), path.getAbsolutePath());
    //     GestorDonantes.getInstance().setDonantes(donantes);
    //
    //     assertEquals(19986, GestorDonantes.getInstance().getDonantes().size(), "No se cargaron la cantidad correcta de donantes");
    //     compararDonantePostCarga(GestorDonantes.getInstance().getDonantes().get(0));
    // }

    @Test
    void segmentacionDonaciones(){
        GestorDonantes.getInstance().agregarDonante(donantePrevioACarga);
        Direccion direccionDeposito = new Direccion("Saraza", "Vegetta", "Willyrex", "777", "S06",
                new Ciudad("CABA", new Provincia("CABA", new Pais("Argentina"))));
        ArrayList<BienDonado> bienesDonados = new ArrayList<>();
        Subcategoria mesa = new Subcategoria("Mesa", "unidad", new Categoria("Mueble", new ArrayList<>(), false, false));
        Subcategoria tomate = new Subcategoria("Tomate", "Kilogramo", new Categoria("Comida", new ArrayList<>(), true, false));
        bienesDonados.add(new BienDonado(2, new Bien("Mesa xd", "", mesa)));
        bienesDonados.add(new BienDonado(2, new Bien("Mesa xD", "", mesa)));
        bienesDonados.add(new BienDonado(1, new BienPerecedero("Tomate", "", tomate, LocalDate.of(2026, 8, 22))));
        bienesDonados.add(new BienDonado(1, new BienPerecedero("Tomate", "", tomate, LocalDate.of(2026, 8, 21))));
        GestorDonaciones.getInstance().donar(new Donacion(direccionDeposito, GestorDonantes.getInstance().getDonantes().getFirst(), "Hola :)", bienesDonados, LocalDate.now()));
        GestorDonaciones.getInstance().segmentarDonaciones();
        ArrayList<DonacionIndependiente> donacionIndependientes = GestorDonaciones.getInstance().getDonacionesIndependientes();
        ArrayList<Integer> cantidadDeBienes = donacionIndependientes.stream().map(d -> d.getBienes().size()).collect(Collectors.toCollection(ArrayList::new));
        assertEquals(3, donacionIndependientes.size());
        assertTrue(cantidadDeBienes.contains(2));
    }

    @Test
    void ejecucionAlgoritmoPrioridadSubatendidos(){
        GestorEntidadesBeneficiarias.getInstance().agregarEntidadBeneficiaria(pepito);
        GestorEntidadesBeneficiarias.getInstance().agregarEntidadBeneficiaria(pepita);

        // Camapaña 1
        GestorEntidadesBeneficiarias.getInstance()
                                    .getEntidad(pepita.getContacto())
                                    .crearCampaniaNecesidad(campañaPepita);
        // Camapaña 2
        GestorEntidadesBeneficiarias.getInstance()
                                    .getEntidad(pepito.getContacto())
                                    .crearCampaniaNecesidad(campañaPepito);

        // Setea que pepito tiene ya una donacion
        GestorEntidadesBeneficiarias.getInstance()
                                    .getEntidad(pepito.getContacto())
                                    .setCantidadDeDonacionesDelCuatrimestre(1);
        DonacionIndependiente donacionIndependiente = new DonacionIndependiente(mesa, null, donacion);
        GestorDonaciones.getInstance()
                        .getDonacionesIndependientes()
                        .add(donacionIndependiente);

        Rankeador rankeador = new Rankeador(new ArrayList<>(Arrays.asList(new AlgoritmoPrioridadSubatendidos())));
        ArrayList<Ranking> rankings = rankeador.generarRankings(GestorDonaciones.getInstance().getDonacionesIndependientes().getFirst());

        assertEquals(1, rankings.size());
        assertEquals(2, rankings.getFirst().getCampañas().size());
        assertEquals(campañaPepita, rankings.getFirst().getCampañas().getFirst());
    }

    @Test
    void ejecucionAlgoritmoCompatibilidadSemantica(){
        GestorEntidadesBeneficiarias.getInstance().agregarEntidadBeneficiaria(pepito);
        GestorEntidadesBeneficiarias.getInstance().agregarEntidadBeneficiaria(pepita);

        // Camapaña 0-9
        for (int i=0; i < 10; i++) {
            CampaniaNecesidadExtraordinaria campaña = new CampaniaNecesidadExtraordinaria(pepita, "ayuda" + i, new ArrayList<>(Arrays.asList(necesidadPepita)), "Tengo hambre", LocalDate.of(2026, 8, 20));
            GestorEntidadesBeneficiarias.getInstance()
                                        .getEntidad(pepita.getContacto())
                                        .crearCampaniaNecesidad(campaña);
        }
        // Camapaña 10-19
        for (int i=0; i < 10; i++) {
            CampaniaNecesidadExtraordinaria campaña = new CampaniaNecesidadExtraordinaria(pepito, "ayuda" + i, new ArrayList<>(Arrays.asList(necesidadPepito)), "Tengo sueño", LocalDate.of(2026, 8, 10));
            GestorEntidadesBeneficiarias.getInstance()
                                        .getEntidad(pepito.getContacto())
                                        .crearCampaniaNecesidad(campaña);
        }

        // Setea que pepito tiene ya una donacion
        GestorEntidadesBeneficiarias.getInstance()
                                    .getEntidad(pepito.getContacto())
                                    .setCantidadDeDonacionesDelCuatrimestre(1);
        DonacionIndependiente donacionIndependiente = new DonacionIndependiente(mesa, null, donacion);
        GestorDonaciones.getInstance()
                        .getDonacionesIndependientes()
                        .add(donacionIndependiente);

        ArrayList<AlgoritmoSeleccion> algoritmos = new ArrayList<>();
        algoritmos.add(new AlgoritmoPrioridadSubatendidos());
        algoritmos.add(new AlgoritmoCompatibilidadSemantica());
        Rankeador rankeador = new Rankeador(algoritmos);
        ArrayList<Ranking> rankings = rankeador.generarRankings(GestorDonaciones.getInstance().getDonacionesIndependientes().getFirst());

        assertEquals(2, rankings.size());
        assertEquals(10, rankings.get(0).getCampañas().size());
        assertEquals(10, rankings.get(1).getCampañas().size());
        for (CampaniaNecesidad campaña : rankings.get(0).getCampañas()) {
            assertEquals(pepito.getRazonSocial(), campaña.getEntidadBeneficiaria().getRazonSocial());
        }
        for (CampaniaNecesidad campaña : rankings.get(0).getCampañas()) {
            assertEquals(pepita.getRazonSocial(), campaña.getEntidadBeneficiaria().getRazonSocial());
        }
    }
}
