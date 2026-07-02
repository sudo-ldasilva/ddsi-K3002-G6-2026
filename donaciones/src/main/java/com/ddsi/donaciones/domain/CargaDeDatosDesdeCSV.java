package com.ddsi.donaciones.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    }

    @Override
    public List<Donante> validarDatos(List<Donante> donantesActuales, String origen) throws Exception {
        List<Map<String, String>> filas = leerCSV(origen);
        List<Donante> candidatos = new ArrayList<>();

        for (Map<String, String> fila : filas) {
            Donante candidato = construirDonante(fila);
            if (candidato != null) {
                if (validador.existeElDonante(donantesActuales, candidato)) {
                    System.out.printf("[ValidadorDeDatos] Posible duplicado: %s%n",
                            fila.getOrDefault("Email", "(sin email)"));
                } else {
                    candidatos.add(candidato);
                }
            }
        }

        return candidatos;
    }

    private void procesarFila(Map<String, String> fila, List<Donante> donantesActuales) {
        String email = fila.getOrDefault("Email", "").trim();
        Contacto contactoMail = new Contacto(email, "mail");

        Donante existente = donantesActuales.stream()
                .filter(d -> d.tienEsteMail(contactoMail))
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

    private void actualizarDonante(Donante donante, Map<String, String> fila) {
        String telefono = fila.getOrDefault("Teléfono", "").trim();
        if (!telefono.isEmpty()) {
            boolean yaEsta = donante.getContactos().stream()
                    .filter(c -> "telefono".equalsIgnoreCase(c.getTipoContacto()))
                    .anyMatch(c -> c.getDireccion().equals(telefono));
            if (!yaEsta) {
                donante.agregarContacto(new Contacto(telefono, "telefono"));
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
        Contacto contactoMail = new Contacto(email, "mail");

        Donante donante;

        switch (tipoPersona) {
            case "HUMANA" -> {
                donante = new PersonaHumana(
                        contactoMail,
                        documento,
                        nombre,
                        18,
                        Genero.OTRO,
                        null,
                        null
                );
            }
            case "JURIDICA" -> {
                donante = new PersonaJuridica(
                        contactoMail,
                        documento,
                        nombre,
                        null,
                        null
                );
            }
            default -> {
                System.out.printf("[CargaCSV] Tipo de persona desconocido: '%s'%n", tipoPersona);
                return null;
            }
        }

        if (!telefono.isEmpty()) donante.agregarContacto(new Contacto(telefono, "telefono"));

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

            headerLine = headerLine.replace("\uFEFF", ""); // eliminar BOM UTF-8

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