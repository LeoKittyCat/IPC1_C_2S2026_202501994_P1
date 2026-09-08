package com.ipc1.proyecto1;

public class EspacioRefugio {

    private int fila;
    private int columna;
    private String area;
    private Animal animal;

    public EspacioRefugio(
            int fila,
            int columna,
            String area) {

        this.fila = fila;
        this.columna = columna;
        this.area = area;

        // null significa que el espacio esta disponible
        this.animal = null;
    }


    // GETTERS

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public String getArea() {
        return area;
    }

    public Animal getAnimal() {
        return animal;
    }


    // SETTER

    public void setAnimal(Animal animal) {

        // Si recibe un Animal, el espacio queda ocupado
        // Si recibe null, el espacio queda libre
        this.animal = animal;
    }


    public boolean estaDisponible() {

        // Si no hay ningun Animal guardado,
        // significa que la jaula/espacio esta libre
        return animal == null;
    }
}