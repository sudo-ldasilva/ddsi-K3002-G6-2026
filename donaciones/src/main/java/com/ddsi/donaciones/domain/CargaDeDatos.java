package com.ddsi.donaciones.domain;

import java.util.List;

public interface CargaDeDatos {

    /**
     * Carga donantes desde la fuente identificada por {@code origen}.
     * @return donantes nuevos o actualizados listos para persistir
     */
    List<Donante> cargarDonantes(List<Donante> donantesActuales, String origen) throws Exception;

    /**
     * Valida los datos crudos antes de procesarlos e informa posibles duplicados.
     * @return donantes validados listos para persistir
     */
    List<Donante> validarDatos(List<Donante> donantesActuales, String origen) throws Exception;
}
