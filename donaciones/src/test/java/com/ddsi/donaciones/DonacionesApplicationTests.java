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

    @BeforeEach
    public void limpiarDonantes() {
        GestorDonantes.getInstance().dropDonantes();
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

    @Test
    void cargaCSV() throws Exception {
        cargarDonanteTemplate();

        File path = new File("../donantes_import_20000_UTF8_BOM.csv");
        CargaDeDatosDesdeCSV carga = new CargaDeDatosDesdeCSV();

        ArrayList<Donante> donantes = carga.cargarDonantes(GestorDonantes.getInstance().getDonantes(), path.getAbsolutePath());
        GestorDonantes.getInstance().setDonantes(donantes);

        assertEquals(19986, GestorDonantes.getInstance().getDonantes().size(), "No se cargaron la cantidad correcta de donantes");
        compararDonantePostCarga(GestorDonantes.getInstance().getDonantes().get(0));
    }

    @Test
    void segmentacionDonaciones(){
        GestorDonantes.getInstance().agregarDonante(donantePrevioACarga);
        Direccion direccionDeposito = new Direccion("Saraza", "Vegetta", "Willyrex", "777", "S06",
                new Ciudad("CABA", new Provincia("CABA", new Pais("Argentina"))));
        ArrayList<BienDonado> bienesDonados = new ArrayList<>();
        Subcategoria mesa = new Subcategoria("Mesa", "unidad", new Categoria("Mueble", new ArrayList<>(), false, false));
        bienesDonados.add(new BienDonado(2, new Bien("Mesa xd", "", mesa)));
        bienesDonados.add(new BienDonado(2, new Bien("Mesa xD", "", mesa)));
        bienesDonados.add(new BienDonado(1, new BienPerecedero("Leche", "", new Subcategoria("Leche", "Litro", new Categoria("Comida", new ArrayList<>(), true, false)), LocalDate.of(2026, 8, 22))));
        bienesDonados.add(new BienDonado(1, new BienPerecedero("Tomate", "", new Subcategoria("Tomate", "Kilogramo", new Categoria("Comida", new ArrayList<>(), true, false)), LocalDate.of(2026, 8, 21))));
        GestorDonaciones.getInstance().donar(new Donacion(direccionDeposito, GestorDonantes.getInstance().getDonantes().getFirst(), "Hola :)", bienesDonados, LocalDate.now()));
        GestorDonaciones.getInstance().segmentarDonaciones();
        ArrayList<DonacionIndependiente> donacionIndependientes = GestorDonaciones.getInstance().getDonacionesIndependientes();
        ArrayList<Integer> cantidadDeBienes = donacionIndependientes.stream().map(d -> d.getBienes().size()).collect(Collectors.toCollection(ArrayList::new));
        assertEquals(3, donacionIndependientes.size());
        assertTrue(cantidadDeBienes.contains(2));
    }
}
