package com.ddsi.donaciones.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//Implementación de CargaDeDatos que lee un archivo CSV con el formato del tp (no se si funciona, matenme pls)
public class CargaDeDatosDesdeCSV implements CargaDeDatos {

    private final ValidadorDeDatos validador;

    public CargaDeDatosDesdeCSV() {
        this.validador = ValidadorDeDatos.conCriteriosPredeterminados();
    }

    public CargaDeDatosDesdeCSV(ValidadorDeDatos validador) {
        this.validador = validador;
    }

    @Override
    public List<Donante> cargarDonantes(List<Donante> donantesActuales, String origen) throws Exception {
        List<Map<String, String>> filas = leerCSV(origen);
        List<Donante> resultado = new ArrayList<>(donantesActuales);

        for (Map<String, String> fila : filas) {
            procesarFila(fila, resultado);
        }

        return resultado;
    }//procesa fila por fila

    @Override
    public List<Donante> validarDatos(List<Donante> donantesActuales, String origen) throws Exception {
        List<Map<String, String>> filas = leerCSV(origen);
        List<Donante> donantes = new ArrayList<>();

        for (Map<String, String> fila : filas) {
            Donante donante = construirDonante(fila);
            if (donante != null) {
                if (validador.existeElDonante(donantesActuales, donante)) {
                    System.out.printf("[ValidadorDeDatos] Posible duplicado detectado: %s%n",
                            fila.getOrDefault("Email", "(sin email)"));//detecta el donante existente y lo evita
                } else {
                    GestorDonantes.registrarDonante(donante);//llamar al gestor donante para que lo añada
                }//si no esta duplicado lo suma
            }
        }

        return donantes;
    }

    private void procesarFila(Map<String, String> fila, List<Donante> donantesActuales) {
        String email = fila.getOrDefault("Email", "").trim();

        // Buscar donante existente por email
        Donante existente = donantesActuales.stream()
                .filter(d -> d.tieneMail(new Contacto(email,"mail")))
                .findFirst()
                .orElse(null);

        if (existente != null) {
            actualizarDonante(existente, fila);
        } else {
            Donante nuevo = construirDonante(fila);
            if (nuevo != null) {
                donantesActuales.add(nuevo);
            }
        }
    }

    //Actualiza los datos de contacto de un donante existente con la info del CSV.
    private void actualizarDonante(Donante donante, Map<String, String> fila) {
        String telefono = fila.getOrDefault("Teléfono", "").trim();
        if (!telefono.isEmpty()) {
            boolean yaEsta = donante.getContactos().stream()
                    .filter(c -> c instanceof Contacto)
                    .anyMatch(c -> c.getDireccion().equals(telefono));
            if (!yaEsta) {
                donante.agregarContacto(new Contacto(donante.getContactos().toString(),"telefono"));
            }
        }
    }


    private Donante construirDonante(Map<String, String> fila) {
        String tipoPersona = fila.getOrDefault("TipoPersona", "").trim().toUpperCase();
        String tipoDocStr  = fila.getOrDefault("TipoDoc", "").trim().toUpperCase();
        String nroDoc      = fila.getOrDefault("Documento", "").trim();
        String nombre      = fila.getOrDefault("Nombre/Razón Social", "").trim();
        String email       = fila.getOrDefault("Email", "").trim();
        String telefono    = fila.getOrDefault("Teléfono", "").trim();

        TipoDocumento tipoDoc = parsearTipoDocumento(tipoDocStr);
        Documento documento   = new Documento(tipoDoc, nroDoc);

        Donante donante;

        switch (tipoPersona) {
            case "HUMANA" -> {
                PersonaHumana humana = new PersonaHumana(
                        documento, nombre,
                        18,           // asumimos un minimo de edad para donar?
                        Genero.OTRO,
                        null,
                        null
                );
                donante = humana;
            }
            case "JURIDICA" -> {
                PersonaJuridica juridica = new PersonaJuridica(
                        documento, nombre,
                        null,
                        null
                );
                donante = juridica;
            }
            default -> {
                System.out.printf("[CargaCSV] Tipo de persona desconocido: '%s'%n", tipoPersona);
                return null;
            }
        }
        return donante;
    }

    private TipoDocumento parsearTipoDocumento(String valor) {
        try {
            return TipoDocumento.valueOf(valor);
        } catch (IllegalArgumentException e) {
            return TipoDocumento.OTRO;
        }
    }

    private List<Map<String, String>> leerCSV(String path) throws Exception {
        List<Map<String, String>> filas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String headerLine = br.readLine();
            if (headerLine == null) throw new Exception("El archivo CSV está vacío: " + path);

            String delimitador = headerLine.contains(";") ? ";" : ",";
            String[] headers   = headerLine.split(delimitador, -1);

            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;

                String[] valores = linea.split(delimitador, -1);
                Map<String, String> fila = new HashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    fila.put(headers[i].trim(), i < valores.length ? valores[i].trim() : "");
                }

                filas.add(fila);
            }
        }

        return filas;
    }
}