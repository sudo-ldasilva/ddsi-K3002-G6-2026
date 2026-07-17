package com.ddsi.logistica.domain;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class GestorRuta {
    private ArrayList<Ruta> rutas;
    private ArrayList<Camion> camiones;

    public GestorRuta(ArrayList<Ruta> rutas, ArrayList<Camion> camiones) {
        this.rutas = new ArrayList<>();
        this.camiones = new ArrayList<>();
    }

    public ArrayList<Ruta> crearRutas(ArrayList<DonacionIndependiente> donaciones) {
        ArrayList<Camion> camionesDisponibles = camiones.stream().filter(c -> c.getDisponible()).collect(Collectors.toCollection(ArrayList::new));

        for (DonacionIndependiente donacion : donaciones) {
            //todo (ya esta la segmentacion de las entregas, falta ver como se asignan a los camiones disponibles y definir que hacemos con las entregas que no se pueden meter en ningun camion, no hay suficientes/disponibles)
        }

        return null;
    }

    public ArrayList<Entrega> armarEntregas(ArrayList<DonacionIndependiente> donaciones) {
        HashMap<Direccion, ArrayList<DonacionIndependiente>> grupos = new HashMap<>();
        ArrayList<Entrega> entregas = new ArrayList<>();
        for (DonacionIndependiente donacion : donaciones) { //Arma en el hashMap los pares de las direcciones de entrega y las donaciones a entregar en dicha direccion
            if(!grupos.containsKey(donacion.getDireccionEntidad()))
                grupos.put(donacion.getDireccionEntidad(), new ArrayList<>());

            grupos.get(donacion.getDireccionEntidad()).add(donacion);

        }

        for (Map.Entry<Direccion, ArrayList<DonacionIndependiente>> entry : grupos.entrySet()) {
            if(this.entraEnAlgunCamion(entry.getValue())){ //Si todas las donaciones entran en algun camion, arma la entrega
                Entrega entrega = new Entrega(entry.getValue(), entry.getValue().getFirst().getRazonSocial(), entry.getValue().getFirst().getDireccionEntidad() ,
                        entry.getValue().getFirst().getDireccionDeposito(), (float) entry.getValue().stream().mapToDouble(DonacionIndependiente::getPeso).sum(),
                        (float) entry.getValue().stream().mapToDouble(DonacionIndependiente::getVolumen).sum());
                entregas.add(entrega);
            } else {
                ArrayList<ArrayList<DonacionIndependiente>> listasDonaciones = separarEntrega(entry.getValue()); //Si no hay ningun camion donde entre, separa las donaciones para poder armar entregas que entren en camiones
                for (ArrayList<DonacionIndependiente> lista : listasDonaciones) { //Por cada lista de donaciones que separo, construye la entrega
                    Entrega entrega = new Entrega(lista, lista.getFirst().getRazonSocial(), lista.getFirst().getDireccionEntidad() ,
                            entry.getValue().getFirst().getDireccionDeposito(), (float) lista.stream().mapToDouble(DonacionIndependiente::getPeso).sum(),
                            (float) lista.stream().mapToDouble(DonacionIndependiente::getVolumen).sum());
                    entregas.add(entrega);
                }
            }

        }

        return entregas;
    }

    public ArrayList<ArrayList<DonacionIndependiente>> separarEntrega(ArrayList<DonacionIndependiente> donaciones) {
        ArrayList<ArrayList<DonacionIndependiente>> listasDonaciones = new ArrayList<>(); //Lista con las listas de donaciones que forman parte de cada entrega
        listasDonaciones.add(donaciones);
        while(listasDonaciones.stream().anyMatch(ld -> !this.entraEnAlgunCamion(ld))) { //Mientras haya listas de donaciones que no entren en un camion, las divide en dos (solo las que no pueden entrar en un camion)
            ArrayList<ArrayList<DonacionIndependiente>> listasDonaciones2 = listasDonaciones.stream().filter(ld -> this.entraEnAlgunCamion(ld)).collect(Collectors.toCollection(ArrayList::new));
            ArrayList<ArrayList<DonacionIndependiente>> listasDonaciones3 = listasDonaciones.stream().filter(ld -> !this.entraEnAlgunCamion(ld)).collect(Collectors.toCollection(ArrayList::new));
            for(ArrayList<DonacionIndependiente> lista : listasDonaciones3) {
                listasDonaciones.add((ArrayList<DonacionIndependiente>) lista.subList(0, lista.size()/2));
                listasDonaciones.add((ArrayList<DonacionIndependiente>)  lista.subList(lista.size()/2, lista.size()));
            }
            listasDonaciones.clear(); //Vacio la lista para armar la nueva (donde posiblemente entren todas las listas en algun camion)
            listasDonaciones.addAll(listasDonaciones2); //Agrego las donaciones que entraban originalmente
            listasDonaciones.addAll(listasDonaciones3); //Agrego las listas nuevas de donaciones
        }
        return listasDonaciones;
    }

    private boolean entraEnAlgunCamion(ArrayList<DonacionIndependiente> donaciones){
        double PesoTotal = donaciones.stream().mapToDouble(DonacionIndependiente::getPeso).sum();
        double VolumenTotal = donaciones.stream().mapToDouble(DonacionIndependiente::getVolumen).sum();
        boolean resultado = false;

        for (Camion camion : camiones) {
            if(camion.getCapacidadCarga() > PesoTotal && camion.getCapacidadVolumen() > VolumenTotal){}
                resultado = true;
        }

        return resultado;
    }


    private String crearMapa(ArrayList<DonacionIndependiente> donaciones){
        ArrayList<String> direcciones = donaciones.stream().map(d -> d.getDireccionEntidad().getDireccion()).distinct().collect(Collectors.toCollection(ArrayList::new));

        StringBuilder url = new StringBuilder("https://www.google.com/maps/dir/");

        for(String d : direcciones){
            url.append(URLEncoder.encode(d, StandardCharsets.UTF_8)).append("/");
        }

        return url.toString();
    }
}
