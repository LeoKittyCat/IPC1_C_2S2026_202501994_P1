package com.ipc1.proyecto1;

public class Solicitud {

    private String codigo;
    private Adoptante adoptante;
    private Animal animal;
    private String estado;

    public Solicitud(String codigo, Adoptante adoptante, Animal animal) {

        this.codigo = codigo;
        this.adoptante = adoptante;
        this.animal = animal;
        this.estado = "Pendiente";
        // Toda solicitud nueva comienza pendiente
    }

    public String getCodigo() {
        return codigo;
    }

    public Adoptante getAdoptante() {
        return adoptante;
    }

    public Animal getAnimal() {
        return animal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}