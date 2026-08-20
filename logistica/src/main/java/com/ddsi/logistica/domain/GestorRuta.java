package com.ddsi.logistica.domain;

import com.ddsi.logistica.dto.RequestDTO;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class GestorRuta {
    private ArrayList<Ruta> rutas;
    private ArrayList<Camion> camiones;
    private ArrayList<Entrega> entregasPendientes;

    public GestorRuta(ArrayList<Ruta> rutas, ArrayList<Camion> camiones) {
        this.rutas = new ArrayList<>();
        this.camiones = new ArrayList<>();
    }

    // public void solicitarCreacionRutas(ArrayList<DonacionIndependiente> donaciones) { //No retorna nada porque la recepcion debe ser realizada por otro metodo
    //     ArrayList<Camion> camionesDisponibles = camiones.stream().filter(c -> c.getDisponible()).collect(Collectors.toCollection(ArrayList::new));
    //     ArrayList<Entrega> entregas = this.armarEntregas(donaciones);
    //     ArrayList<RequestDTO> peticiones =  new ArrayList<>();
    // }

    public ArrayList<Entrega> armarEntregas(ArrayList<DonacionIndependiente> donaciones) {
        HashMap<Direccion, ArrayList<DonacionIndependiente>> grupos = new HashMap<>();
        ArrayList<Entrega> entregas = new ArrayList<>();

        //Arma en el hashMap los pares de las direcciones de entrega y las donaciones a entregar en dicha direccion
        for (DonacionIndependiente donacion : donaciones) {
            if (!grupos.containsKey(donacion.getDireccionEntidad())) {
                grupos.put(donacion.getDireccionEntidad(), new ArrayList<>());
            }
            grupos.get(donacion.getDireccionEntidad()).add(donacion);
        }

        for (Map.Entry<Direccion, ArrayList<DonacionIndependiente>> entry : grupos.entrySet()) {
            //Si todas las donaciones entran en algun camion, arma la entrega
            if (this.entraEnAlgunCamion(entry.getValue())) {
                entregas.add(new Entrega(
                    entry.getValue(),
                    entry.getValue().getFirst().getRazonSocial(),
                    entry.getValue().getFirst().getDireccionEntidad(),
                    entry.getValue().getFirst().getDireccionDeposito(),
                    (float) entry.getValue().stream().mapToDouble(DonacionIndependiente::getPeso).sum(),
                    (float) entry.getValue().stream().mapToDouble(DonacionIndependiente::getVolumen).sum()
                ));
            } else {
                //Si no hay ningún camión donde entre, separa las donaciones para poder armar entregas que entren en camiones
                ArrayList<ArrayList<DonacionIndependiente>> listasDonaciones = this.separarEntrega(entry.getValue());
                // Por cada lista de donaciones que separo, construye la entrega
                for (ArrayList<DonacionIndependiente> lista : listasDonaciones) {
                    entregas.add(new Entrega(
                        lista,
                        lista.getFirst().getRazonSocial(),
                        lista.getFirst().getDireccionEntidad(),
                        entry.getValue().getFirst().getDireccionDeposito(),
                        (float) lista.stream().mapToDouble(DonacionIndependiente::getPeso).sum(),
                        (float) lista.stream().mapToDouble(DonacionIndependiente::getVolumen).sum()
                    ));
                }
            }
        }

        return entregas;
    }

    private ArrayList<ArrayList<DonacionIndependiente>> separarEntrega(ArrayList<DonacionIndependiente> donaciones) {
        ArrayList<ArrayList<DonacionIndependiente>> listasDonaciones = new ArrayList<>(); //Lista con las listas de donaciones que forman parte de cada entrega
        listasDonaciones.add(donaciones);

        while(listasDonaciones.stream().anyMatch(ld -> !this.entraEnAlgunCamion(ld))) { //Mientras haya listas de donaciones que no entren en un camion, las divide en dos (solo las que no pueden entrar en un camion)
            ArrayList<ArrayList<DonacionIndependiente>> entran = listasDonaciones.stream()
                                                                                 .filter(ld -> this.entraEnAlgunCamion(ld))
                                                                                 .collect(Collectors.toCollection(ArrayList::new));

            ArrayList<ArrayList<DonacionIndependiente>> noEntran = listasDonaciones.stream()
                                                                                   .filter(ld -> !this.entraEnAlgunCamion(ld))
                                                                                   .collect(Collectors.toCollection(ArrayList::new));

            listasDonaciones.clear(); // Vacio la lista para armar la nueva (donde posiblemente entren todas las listas en algun camion)
            listasDonaciones.addAll(entran); // Agrego las donaciones que entraban originalmente
            for (ArrayList<DonacionIndependiente> lista : noEntran) {
                listasDonaciones.add((ArrayList<DonacionIndependiente>) lista.subList(0, lista.size()/2));
                listasDonaciones.add((ArrayList<DonacionIndependiente>)  lista.subList(lista.size()/2, lista.size()));
            }
        }
        return listasDonaciones;
    }

    private boolean entraEnAlgunCamion(ArrayList<DonacionIndependiente> donaciones){
        double pesoTotal = donaciones.stream().mapToDouble(DonacionIndependiente::getPeso).sum();
        double volumenTotal = donaciones.stream().mapToDouble(DonacionIndependiente::getVolumen).sum();

        for (Camion camion : camiones) {
            if (camion.getCapacidadCarga() > pesoTotal && camion.getCapacidadVolumen() > volumenTotal) {
                return true;
            }
        }

        return false;
    }

    public String crearMapa(ArrayList<DonacionIndependiente> donaciones) {
        ArrayList<String> direcciones = donaciones.stream()
                                                  .map(d -> d.getDireccionEntidad().getDireccion())
                                                  .distinct()
                                                  .collect(Collectors.toCollection(ArrayList::new));

        // TODO Podríamos tener un adapter para los servicios de mapeo. En este caso (por ahora) lo hard-codeamos. Perdón... :'(
        StringBuilder url = new StringBuilder("https://www.google.com/maps/dir/");
        for (int i = 0; i < direcciones.size(); i++) {
            url.append(direcciones.get(i).replace(" ", "+"));
            if (i < direcciones.size() - 1) url.append("/");
        }

        return url.toString();
    }
}
