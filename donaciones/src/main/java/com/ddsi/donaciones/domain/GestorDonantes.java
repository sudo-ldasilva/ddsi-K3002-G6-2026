package com.ddsi.donaciones.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

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
        donantesRegistrados.add(donante);
    }

    // import com.ddsi.donaciones.domain.GestorDonantes;
    //
    // try {
    //     GestorDonantes.getInstance().cargarCSV("/home/leonardo/archivo.csv");
    // } catch (FileNotFoundException e) {
    //     System.out.println("Que no panda el cúnico");
    // }

    public void cargarCSV(String path) throws FileNotFoundException {
        File csv = new File(path);
        Scanner scannerRegistros = new Scanner(csv);

        // HEADER
        String header = scannerRegistros.nextLine();
        Scanner scannerHeader = new Scanner(header);
        scannerHeader.useDelimiter(", ");

        System.out.println("Header: " + header);
        while (scannerHeader.hasNext()) {
            String campo = scannerHeader.next();
            System.out.println("\t- Header Campo: " + campo);
        }
        scannerHeader.close();

        // DATOS
        while (scannerRegistros.hasNextLine()) {
            String registro = scannerRegistros.nextLine();
            Scanner scannerRegistro = new Scanner(registro);
            scannerRegistro.useDelimiter(", ");

            System.out.println("Registro: " + registro);
            while (scannerRegistro.hasNext()) {
                String campo = scannerRegistro.next();
                System.out.println("\t- Campo: " + campo);
            }

            scannerRegistro.close();
        }

        scannerRegistros.close();
    }
}
