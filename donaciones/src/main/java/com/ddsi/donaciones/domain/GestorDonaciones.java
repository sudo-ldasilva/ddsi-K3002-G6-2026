package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.ddsi.donaciones.service.NotificacionDispatcherService;

public class GestorDonaciones {
    private static GestorDonaciones gestorDonaciones = null;
    private ArrayList<Donacion> donaciones;
    private ArrayList<DonacionIndependiente> posiblesDonaciones;

    private GestorDonaciones() {
        this.donaciones = new ArrayList<>();
        this.posiblesDonaciones = new ArrayList<>();
    }

    public static GestorDonaciones getInstance() {
        if (gestorDonaciones == null) {
            gestorDonaciones = new GestorDonaciones();
        }
        return gestorDonaciones;
    }

    public void donar(Donacion donacion) {
        donaciones.add(donacion);
    }

    public ArrayList<Donacion> getDonaciones() {
        return donaciones;
    }

    public ArrayList<DonacionIndependiente> getDonacionesIndependientes() {
        return posiblesDonaciones;
    }

    public DonacionIndependiente getDonacionIndependienteByUUID(UUID uuid) {
        for (int i = 0; i < posiblesDonaciones.size(); i++) {
            DonacionIndependiente donacion = posiblesDonaciones.get(i);
            if (donacion.getUUID().equals(uuid)) {
                return donacion;
            }
        }
        return null;
    }

    public Donacion getDonacionByUUID(UUID uuid) {
        for (int i = 0; i < donaciones.size(); i++) {
            Donacion donacion = donaciones.get(i);
            if (donacion.getUUID().equals(uuid)) {
                return donacion;
            }
        }
        return null;
    }

    public Donacion eliminarDonacionByUUID(UUID uuid) {
        for (int i = 0; i < donaciones.size(); i++) {
            if (donaciones.get(i).getUUID().equals(uuid)) {
                Donacion donacion = donaciones.remove(i);
                return donacion;
            }
        }
        return null;
    }

    public void segmentarDonaciones() {
        donaciones.stream().filter(d->!d.yaFueSegmentada()).forEach(d->{this.generarDonacionesIndependientes(d);});
    }

    public void generarDonacionesIndependientes(Donacion donacion){
        ArrayList<DonacionIndependiente> donacionesInd = new ArrayList<>();

        for (BienDonado bien : donacion.getBienes()) {//para vencidos
            if (bien.getBien() instanceof BienPerecedero bienPerecedero) {
                if (bienPerecedero.estaVencido()) {
                    continue;
                }
            }
            Subcategoria sc = bien.getBien().getSubcategoria();

            DonacionIndependiente donacionInd =
                donacionesInd
                .stream()
                .filter(
                        d -> d.getSubcategoria().equals(sc)
                        && (
                            !sc.getCategoria().esPerecedero()
                            || !d.getSubcategoria().getCategoria().esPerecedero()
                            || ((BienPerecedero) d.getBienes().get(0).getBien()).getFechaVencimiento().equals(((BienPerecedero) bien.getBien()).getFechaVencimiento())
                        )
                )
                .findFirst()
                .orElse(null); // Devuelve el objeto si lo encuentra, o null si no

            if (donacionInd == null) {
                donacionInd = new DonacionIndependiente(sc, null, donacion);
                donacionesInd.add(donacionInd);
            }

            donacionInd.agregarBien(bien);
        }

        donacion.marcarSegmentada();

        posiblesDonaciones.addAll(donacionesInd);
    }

    public void asignarDonacionIndependiente(UUID donacionUUID, UUID campaniaUUID) throws Exception {
        DonacionIndependiente donacionIndependiente = this.getDonacionIndependienteByUUID(donacionUUID);
        if (donacionIndependiente != null) {
            throw new Exception();
        }

        CampaniaNecesidad campania = GestorEntidadesBeneficiarias.getInstance().obtenerCampaniaDeNecesidad(campaniaUUID);
        NecesidadIndividual necesidadAsignada = campania.necesidades.stream().filter(n->n.getSubcategoria().equals(donacionIndependiente.getSubcategoria())).findFirst().orElse(null);
        if (necesidadAsignada == null || necesidadAsignada.estaCubierta()) {
            throw new Exception();
        }

        //Asignacion
        necesidadAsignada.recibir(donacionIndependiente);
        donacionIndependiente.setNecesidad(necesidadAsignada);
        campania.getEntidadBeneficiaria().sumarDonacionCuatrimestral();

        //Cambio de Estado
        donacionIndependiente.cambiarEstado(EstadoDeDonacion.ASIGNACION_REALIZADA);

        //Envio de notificaciones
        NotificacionDispatcherService notificacionDispatcherService = new NotificacionDispatcherService();
        ArrayList<Contacto> contactosDonante = new ArrayList<>(donacionIndependiente.getDonacion().getDonante().getContactos());
        contactosDonante.add(0, donacionIndependiente.getDonacion().getDonante().getMail());
        notificacionDispatcherService.notificar(contactosDonante,
                String.format("Asignacion de Donacion: Se asigno el bien %s a la entidad %s", donacionIndependiente.getSubcategoria(), campania.getEntidadBeneficiaria().getRazonSocial()));
        ArrayList<Contacto> contactoEntidad = new ArrayList<>();
        contactoEntidad.add(campania.getEntidadBeneficiaria().getContacto());
        notificacionDispatcherService.notificar(contactoEntidad, String.format("Asignacion de Donacion: Se te asigno el bien %s", donacionIndependiente.getSubcategoria()));
    }

}
