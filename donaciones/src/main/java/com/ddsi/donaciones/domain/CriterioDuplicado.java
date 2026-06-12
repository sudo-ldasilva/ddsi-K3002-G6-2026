package com.ddsi.donaciones.domain;

//no se como hacerlo si no es con una interfaz strategy pero no se si me gusta tampoco
//polimorfismo por ahi?
public interface CriterioDuplicado {

    boolean sonPosibleDuplicado(Donante existente, Donante candidato);
}
