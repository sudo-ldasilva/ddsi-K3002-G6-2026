package com.ddsi.donaciones.domain.dto;

import com.ddsi.donaciones.domain.Contacto;
import com.ddsi.donaciones.domain.Donante;
import com.ddsi.donaciones.domain.EntidadBeneficiaria;
import com.ddsi.donaciones.domain.PersonaHumana;
import com.ddsi.donaciones.domain.PersonaJuridica;

public class DestinatarioNotificacionMapper {

    public static DestinatarioNotificacionDTO desdeDonante(Donante donante) {
        Contacto contacto = obtenerContacto(donante);
        String nombre = obtenerNombre(donante);

        if (contacto == null) {
            throw new IllegalStateException("El donante no tiene un medio de contacto definido");
        }

        return new DestinatarioNotificacionDTO(nombre, contacto.getTipoContacto(), contacto.getDireccion());
    }

    public static DestinatarioNotificacionDTO desdeEntidadBeneficiaria(EntidadBeneficiaria entidad) {
        if (entidad.getContacto() == null) {
            throw new IllegalStateException("La entidad beneficiaria no tiene un medio de contacto definido");
        }

        return new DestinatarioNotificacionDTO(
                entidad.getRazonSocial(),
                entidad.getContacto().getTipoContacto(),
                entidad.getContacto().getDireccion()
        );
    }

    private static Contacto obtenerContacto(Donante donante) {
        if (donante instanceof PersonaHumana persona) {
            return persona.getMedioPredeterminado();
        }
        if (donante instanceof PersonaJuridica juridica) {
            return juridica.getContactos().isEmpty() ? null : juridica.getContactos().get(0);
        }
        return null;
    }

    private static String obtenerNombre(Donante donante) {
        if (donante instanceof PersonaHumana persona) {
            return persona.getNombreYApellido();
        }
        if (donante instanceof PersonaJuridica juridica) {
            return juridica.getRazonSocial();
        }
        return "Desconocido";
    }
}
