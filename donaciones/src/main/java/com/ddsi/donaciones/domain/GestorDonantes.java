package com.ddsi.donaciones.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class GestorDonantes {
    private static GestorDonantes gestorDonantes = null;
    private ArrayList<Donante> donantesRegistrados = null;

    private GestorDonantes() {
        donantesRegistrados = new ArrayList<>();
    }

    public ArrayList<Donante> getDonantes() {
        return this.donantesRegistrados;
    }

    public Donante getDonante(ContactoMail mail) {
        for (int i = 0; i < donantesRegistrados.size(); i++) {
            if (donantesRegistrados.get(i).getMail().equals(mail)) {
                return donantesRegistrados.get(i);
            }
        }
        return null;
    }

    public Donante eliminarDonante(UUID uuid) {
        for (int i = 0; i < donantesRegistrados.size(); i++) {
            if (donantesRegistrados.get(i).getUUID().equals(uuid)) {
                return donantesRegistrados.remove(i);
            }
        }
        return null;
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

        // DATOS
        while (scannerRegistros.hasNextLine()) {
            ArrayList<String> camposDeDonanteNuevo = new ArrayList<String>();

            String registro = scannerRegistros.nextLine();
            Scanner scannerRegistro = new Scanner(registro);
            scannerRegistro.useDelimiter(",");

            while (scannerRegistro.hasNext()) {
                camposDeDonanteNuevo.add(scannerRegistro.next());
            }

            int posicionMail = posiciones.get("Email");
            boolean yaEstaCargado = donantesRegistrados
                                    .stream()
                                    .anyMatch( (donante) -> donante.tieneMail(new Contacto(camposDeDonanteNuevo.get(posicionMail),"mail")));

            if (yaEstaCargado) {
                Donante donanteAActualizar = donantesRegistrados
                                             .stream()
                                             .filter( (donante) -> donante.tieneMail(new Contacto(camposDeDonanteNuevo.get(posicionMail),"mail")))
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
                if (Objects.equals(camposDeDonanteNuevo.get(posiciones.get("TipoPersona")), "HUMANA")) {
                    PersonaHumana nuevoDonanteHumano = new PersonaHumana(
                        null,
                        camposDeDonanteNuevo.get(posiciones.get("Nombre/Razón Social")),
                        // Estos no están en el CSV de ejemplo:
                        0,              // TODO: camposDeDonanteNuevo.get(posicionesHeader.get("Edad")),
                        Genero.OTRO,    // TODO: Genero.fromString(camposDeDonanteNuevo.get(posicionesHeaders.get("Genero))),
                        null,           // TODO: Direccion.fromString(camposDeDonanteNuevo.get(posicionesHeaders.get("Dirección"))),
                        null            // TODO: medio predeterminado
                    );

                    nuevoDonanteHumano.agregarContacto(new Contacto(camposDeDonanteNuevo.get(posiciones.get("Telefono")), "telefono"));//dios sabra como hago esto
                    this.donantesRegistrados.add(nuevoDonanteHumano);
                } else {
                    PersonaJuridica nuevoDonanteJuridico = new PersonaJuridica(
                        null,
                        camposDeDonanteNuevo.get(posiciones.get("Nombre/Razón Social")),
                        null,
                        null
                    );
                    nuevoDonanteJuridico.agregarContacto(new Contacto(camposDeDonanteNuevo.get(posiciones.get("Email")),"mail")); //agregar contacto se usa polimorficamente, ver como arreglo esto
                    nuevoDonanteJuridico.agregarContacto(new Contacto(camposDeDonanteNuevo.get(posiciones.get("Telefono")), "telefono"));
                    this.donantesRegistrados.add(nuevoDonanteJuridico);
                }
            }
            scannerRegistro.close();
        }
        scannerRegistros.close();
    }
}
