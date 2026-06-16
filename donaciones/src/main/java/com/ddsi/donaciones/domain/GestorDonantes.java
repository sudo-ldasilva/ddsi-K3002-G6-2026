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

    public Donante getDonante(Contacto mail) {
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

}
