package com.ddsi.donaciones.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public ArrayList<Donacion> getDonacionesByDonante(Donante donante) {
        return donaciones.stream().filter(d -> d.getDonante().equals(donante)).collect(Collectors.toCollection(ArrayList::new));
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
        donacionIndependiente.cambiarEstado(new EstadoDonacion(EstadoDeDonacion.ASIGNACION_REALIZADA, new Date()));

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

    public void cambiarEstadoDonacionesVencidas() {
        posiblesDonaciones
            .stream()
            .filter(d ->
                d.getEstadoActual().getEstado() == EstadoDeDonacion.EN_DEPOSITO
                && d.getSubcategoria().getCategoria().esPerecedero()
                && ((BienPerecedero) d.getBienes().get(0).getBien()).estaVencido()
            )
            .forEach(d -> d.cambiarEstado(new EstadoDonacion(EstadoDeDonacion.VENCIDA, new Date())));
    }

    public void recibirAsignacionDeRuta(ArrayList<UUID> donacionesIndependienteUUID) {
        donacionesIndependienteUUID.stream().forEach(donacionIndependienteUUID -> {
            DonacionIndependiente donacionInd = getDonacionIndependienteByUUID(donacionIndependienteUUID);

            donacionInd.cambiarEstado(new EstadoDonacion(EstadoDeDonacion.ASIGNACION_REALIZADA, new Date()));
            NotificacionDispatcherService notif = new NotificacionDispatcherService();
            notif.notificar(donacionInd.getDonacion().getDonante().getContactos(), "Su donación fue asignada!");
            ArrayList<Contacto> contactosEntidad = new ArrayList<>(); contactosEntidad.add(donacionInd.getNecesidad().getCampania().getEntidadBeneficiaria().getContacto());
            notif.notificar(contactosEntidad, "Su donación fue asignada!");
        });
    }

    public void recibirInicioDeRuta(ArrayList<UUID> donacionesIndependienteUUID, String enlaceMapa) {
        donacionesIndependienteUUID.stream().forEach(donacionIndependienteUUID -> {
            DonacionIndependiente donacionInd = getDonacionIndependienteByUUID(donacionIndependienteUUID);

            donacionInd.cambiarEstado(new EstadoDonacion(EstadoDeDonacion.EN_TRASLADO, new Date()));
            NotificacionDispatcherService notif = new NotificacionDispatcherService();
            notif.notificar(donacionInd.getDonacion().getDonante().getContactos(), "Su donación está en camino! Mapa: " + enlaceMapa);
            ArrayList<Contacto> contactosEntidad = new ArrayList<>(); contactosEntidad.add(donacionInd.getNecesidad().getCampania().getEntidadBeneficiaria().getContacto());
            notif.notificar(contactosEntidad, "Su donación está en camino! Mapa: " + enlaceMapa);
        });
    }

    public void recibirEntregaExitosa(UUID donacionIndependiente, ComprobanteRecepcion comprobante) {
        DonacionIndependiente donacionInd = getDonacionIndependienteByUUID(donacionIndependiente);

        donacionInd.cambiarEstado(new EstadoDonacion(EstadoDeDonacion.ENTREGADA, new Date()));
        NotificacionDispatcherService notif = new NotificacionDispatcherService();
        notif.notificar(donacionInd.getDonacion().getDonante().getContactos(), "Su donación fue entregada! Comprobante: " + comprobante.toString());
        ArrayList<Contacto> contactosEntidad = new ArrayList<>(); contactosEntidad.add(donacionInd.getNecesidad().getCampania().getEntidadBeneficiaria().getContacto());
        notif.notificar(contactosEntidad, "Su donación fue entregada! Comprobante: " + comprobante.toString());
    }

    public void recibirEntregaFallida(UUID donacionIndependiente, String justificacion, boolean puedeReasignarse) {
        DonacionIndependiente donacionInd = getDonacionIndependienteByUUID(donacionIndependiente);

        donacionInd.cambiarEstado(new EstadoDonacion(EstadoDeDonacion.ENTREGA_FALLIDA, new Date()));
        NotificacionDispatcherService notif = new NotificacionDispatcherService();
        notif.notificar(donacionInd.getDonacion().getDonante().getContactos(), "Su donación no se pudo entregar :( Justificación: " + justificacion);
        ArrayList<Contacto> contactosEntidad = new ArrayList<>(); contactosEntidad.add(donacionInd.getNecesidad().getCampania().getEntidadBeneficiaria().getContacto());
        notif.notificar(contactosEntidad, "Su donación no se pudo entregar :( Justificación: " + justificacion);

        if (puedeReasignarse) {
            donacionInd.cambiarEstado(new EstadoDonacion(EstadoDeDonacion.EN_DEPOSITO, new Date()));
        }
    }
}
