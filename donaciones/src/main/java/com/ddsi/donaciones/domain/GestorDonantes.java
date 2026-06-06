package com.ddsi.donaciones.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorDonantes {
    private static GestorDonantes gestorDonantes = null;
    private ArrayList<Donante> donantesRegistrados = null;

    private GestorDonantes() {
        donantesRegistrados = new ArrayList<>();
    }

    public static GestorDonantes getInstance() {
        if (gestorDonantes == null) {
            gestorDonantes = new GestorDonantes();
        }

        return gestorDonantes;
    }

    public void registrarDonante(Donante donante) {
        this.donantesRegistrados.add(donante);
    }

    // import com.ddsi.donaciones.domain.GestorDonantes;
    //
    // try {
    //     GestorDonantes.getInstance().cargarCSV("/home/leonardo/archivo.csv");
    // } catch (FileNotFoundException e) {
    //     System.out.println("Que no panda el cúnico");
    // }

    private HashMap<String, Integer> parsearHeaderCSV(String header) {
        HashMap<String, Integer> posicionesHeaders = new HashMap<>();

        Scanner scannerHeader = new Scanner(header);
        scannerHeader.useDelimiter(",");

        System.out.println("Header: " + header);
        for (Integer posicion = 0; scannerHeader.hasNext(); posicion++) {
            String campo = scannerHeader.next();
            posicionesHeaders.put(campo, posicion);
            // System.out.println("\t- Header Campo: " + campo);
        }
        scannerHeader.close();

        return posicionesHeaders;
    }

    // TODO Pasar a Carga de Datos CSV
    public void cargarCSV(String path) throws Exception, FileNotFoundException {
        File csv = new File(path);
        Scanner scannerRegistros = new Scanner(csv);

        // HEADER
        String header = scannerRegistros.nextLine();
        HashMap<String, Integer> posiciones = parsearHeaderCSV(header);
        // posicionesHeaders.get("TipoPersona") retorna 0;
        // posicionesHeaders.get("TipoDoc") retorna 1;
        // ... y así
        // (entonces no dependemos del orden en el que se guardaron las columnas)

        // DATOS
        while (scannerRegistros.hasNextLine()) {
            ArrayList<String> camposDeDonanteNuevo = new ArrayList<String>();

            String registro = scannerRegistros.nextLine();
            Scanner scannerRegistro = new Scanner(registro);
            scannerRegistro.useDelimiter(",");

            // LEER campos donante
            // System.out.println("Registro: " + registro);
            while (scannerRegistro.hasNext()) {
                camposDeDonanteNuevo.add(scannerRegistro.next());
                // System.out.println("\t- Campo: " + campo);
            }

            int posicionDocumento = posiciones.get("Documento");
            boolean yaEstaCargado = donantesRegistrados
                                    .stream()
                                    .anyMatch( (donante) -> donante.getDocumento().equals(camposDeDonanteNuevo.get(posicionDocumento)));

            if (yaEstaCargado) {
                Donante donanteAActualizar = donantesRegistrados
                                             .stream()
                                             .filter( (donante) -> donante.getDocumento().equals(camposDeDonanteNuevo.get(posicionDocumento)))
                                             .findFirst()
                                             .orElse(null);

                if (donanteAActualizar == null) {
                    scannerRegistro.close();
                    scannerRegistros.close();
                    throw new Exception("Donante está registrado pero no se pudo encontrar");
                }

                // TODO donanteAActualizar.cargarDesdeCSV(posicionesHeader, camposDeDonanteNuevo)

            } else {
                // TODO donanteAActualizar.cargarDesdeCSV(posicionesHeader, camposDeDonanteNuevo)
                // Para cumplir con el polimorfismo
                if (camposDeDonanteNuevo.get(posiciones.get("TipoPersona")) == "HUMANA") {
                    PersonaHumana nuevoDonanteHumano = new PersonaHumana(
                        camposDeDonanteNuevo.get(posicionDocumento),
                        camposDeDonanteNuevo.get(posiciones.get("Nombre/Razón Social")),
                        // Estos no están en el CSV de ejemplo:
                        0,              // TODO: camposDeDonanteNuevo.get(posicionesHeader.get("Edad")),
                        Documento.OTRO, // TODO: Documento.fromString(camposDeDonanteNuevo.get(posicionesHeaders.get("TipoDoc"))),
                        Genero.OTRO,    // TODO: Genero.fromString(camposDeDonanteNuevo.get(posicionesHeaders.get("Genero))),
                        null,           // TODO: Direccion.fromString(camposDeDonanteNuevo.get(posicionesHeaders.get("Dirección"))),
                        null            // TODO: medio predeterminado
                    );

                    nuevoDonanteHumano.agregarContacto(new ContactoMail(camposDeDonanteNuevo.get(posiciones.get("Email"))));
                    nuevoDonanteHumano.agregarContacto(new ContactoTelefono(camposDeDonanteNuevo.get(posiciones.get("Teléfono"))));
                    this.donantesRegistrados.add(nuevoDonanteHumano);
                } else {
                    PersonaJuridica nuevoDonanteJuridico = new PersonaJuridica(
                        camposDeDonanteNuevo.get(posiciones.get("Documento")),
                        camposDeDonanteNuevo.get(posiciones.get("Nombre/Razón Social")),
                        null,
                        null
                    );
                    nuevoDonanteJuridico.agregarContacto(new ContactoMail(camposDeDonanteNuevo.get(posiciones.get("Email"))));
                    nuevoDonanteJuridico.agregarContacto(new ContactoTelefono(camposDeDonanteNuevo.get(posiciones.get("Teléfono"))));
                    this.donantesRegistrados.add(nuevoDonanteJuridico);
                }
            }
            scannerRegistro.close();
        }

        scannerRegistros.close();
    }
}
