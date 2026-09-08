package com.ipc1.proyecto1;

public class GestionUbicaciones {

    private EspacioRefugio[][] espacios;

    public GestionUbicaciones() {

        // Matriz de 3 filas y 5 columnas
        // Cada fila representa un area y cada columna un espacio
        espacios = new EspacioRefugio[3][5];

        // Llena toda la matriz con objetos EspacioRefugio
        // Esto primero recorre las filas, pero a su vez recorre todas las columnas de dicha fila
        for (int fila = 0; fila < espacios.length; fila++) {

            for (int columna = 0; columna < espacios[fila].length; columna++) {

                String area = obtenerNombreArea(fila);

                espacios[fila][columna] =
                        new EspacioRefugio(fila, columna, area);
            }
        }
    }


    // =========================
    // OBTENER NOMBRE DEL AREA
    // =========================
    public String obtenerNombreArea(int fila) {

        // Cada numero de fila representa un area diferente
        if (fila == 0) {
            return "Perros";
        }

        if (fila == 1) {
            return "Gatos";
        }

        if (fila == 2) {
            return "Veterinaria";
        }

        return "Desconocida";
    }


    // =========================
    // VALIDAR POSICION
    // =========================
    public boolean posicionValida(int fila, int columna) {

        // Evita intentar entrar a una posicion que no existe en la matriz
        return fila >= 0 && fila < espacios.length && columna >= 0 && columna < espacios[fila].length;
    } // esto devuelve true //Fila no puede ser negativa, ni la columna, y revisa que la columna no sea menor a la longitud de las filas


    // =========================
    // CONSULTAR DISPONIBILIDAD
    // =========================
    public boolean estaDisponible(int fila, int columna) {

        // Primero revisa que la posicion realmente exista
        if (!posicionValida(fila, columna)) {
            return false;
        }

        // El mismo EspacioRefugio revisa si su animal es null
        return espacios[fila][columna].estaDisponible();
    }


    // =========================
    // ASIGNAR ANIMAL
    // =========================
    public boolean asignarAnimal(
            int fila,
            int columna,
            Animal animal) {

        // No se puede asignar nada si la posicion no existe
        if (!posicionValida(fila, columna)) {
            return false;
        }

        // Tampoco se puede meter otro animal si el espacio ya esta ocupado
        if (!espacios[fila][columna].estaDisponible()) {
            return false;
        }

        // Guarda la referencia del Animal dentro de este espacio
        espacios[fila][columna].setAnimal(animal);

        return true;
    }


    // =========================
    // LIBERAR ESPACIO
    // =========================
    public boolean liberarEspacio(int fila, int columna) {

        // Primero revisa que la posicion exista
        if (!posicionValida(fila, columna)) {
            return false;
        }

        // Si ya estaba vacio no hay nada que liberar
        if (espacios[fila][columna].estaDisponible()) {
            return false;
        }

        // null significa que este espacio ya no tiene ningun animal
        espacios[fila][columna].setAnimal(null);

        return true;
    }


    // =========================
    // MOSTRAR MATRIZ
    // =========================
    public void mostrarMatriz() {

        System.out.println("\n=== UBICACIONES DEL REFUGIO ===");

        for (int fila = 0; fila < espacios.length; fila++) {

            System.out.print(
                    obtenerNombreArea(fila) + ": "
            );

            for (int columna = 0;
                    columna < espacios[fila].length;
                    columna++) {

                EspacioRefugio espacio =
                        espacios[fila][columna];

                if (espacio.estaDisponible()) {

                    // L significa Libre
                    System.out.print("[L] ");

                } else {

                    // Si esta ocupado muestra el codigo del animal
                    System.out.print(
                            "[" + espacio.getAnimal().getCodigo() + "] "
                    );
                }
            }

            System.out.println();
        }
    }
    
    // =========================
    // VALIDAR AREA SEGUN ESPECIE
    // =========================

    public boolean areaValidaParaAnimal(int fila, Animal animal) {

        // La fila 0 es solo para perros
        if (fila == 0) {
            return animal.getEspecie().equalsIgnoreCase("Perro");
        }

        // La fila 1 es solo para gatos
        if (fila == 1) {
            return animal.getEspecie().equalsIgnoreCase("Gato");
        }

        // Veterinaria puede recibir perros o gatos
        if (fila == 2) {
            return true;
        }

        return false;
    }
    // Busca si el animal ya esta asignado en algun espacio
    public boolean animalYaAsignado(Animal animal) {

        for (int fila = 0; fila < espacios.length; fila++) {

            for (int columna = 0; columna < espacios[fila].length; columna++) {

                if (!espacios[fila][columna].estaDisponible()
                        && espacios[fila][columna].getAnimal() == animal) {

                    return true;
                }
            }
        }

        return false;
    }
}