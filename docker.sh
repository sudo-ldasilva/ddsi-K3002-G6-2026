#!/bin/bash

build_docker() {
    echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
    echo "DOCKER BUILD $1"
    echo "~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
    cd $1
    docker build -t $1 .
    cd ..
}

export_docker() {
    docker save -o "$1.tar" $1
}

build_docker autenticacion
build_docker donaciones
build_docker incentivos
build_docker logistica
build_docker notificaciones

mkdir -p docker && cd docker

export_docker autenticacion
export_docker donaciones
export_docker incentivos
export_docker logistica
export_docker notificaciones

# Para ejecutar una imagen: `docker run -p 8080:8080 [SERVICIO]`
# En otra computadora se puede importar con: `docker load -i [SERVICIO].tar`
