package com.ddsi.donaciones.domain;

import java.util.List;

public interface CargaDeDatos {

    List<Donante> cargarDonantes(List<Donante> donantesActuales, String origen) throws Exception;

    List<Donante> validarDatos(List<Donante> donantesActuales, String origen) throws Exception;
}
