package com.ddsi.donaciones.domain;

import java.util.ArrayList;

public interface CargaDeDatos {

    ArrayList<Donante> cargarDonantes(ArrayList<Donante> donantesActuales, String origen) throws Exception;

    ArrayList<Donante> validarDatos(ArrayList<Donante> donantesActuales, String origen) throws Exception;
}
