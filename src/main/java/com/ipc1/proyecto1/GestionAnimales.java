package com.ipc1.proyecto1;

public class GestionAnimales {

    private Animal[] animales;
    private int cantidadAnimales;

    public GestionAnimales() {
        animales = new Animal[100];
        cantidadAnimales = 0;
    }

    public boolean registrarAnimal(Animal nuevoAnimal) {

        if (cantidadAnimales >= animales.length) { //Evitamos maas de 100 animales
            return false;
        }

        for (int i = 0; i < cantidadAnimales; i++) { //No recorremos los 100 espacios, solo recorremos la cantidad de espacios que haya ocupados, si tenemos 7 animales va a recorrer 0,1,2,3,4,5,6
            if (animales[i].getCodigo().equalsIgnoreCase(nuevoAnimal.getCodigo())) { //equalsIgnoreCase significa que va a evitar codigos tipo A001, a001 y asi
                return false;
            }
        }

        animales[cantidadAnimales] = nuevoAnimal;
        cantidadAnimales++;

        return true;
    }

    public Animal buscarPorCodigo(String codigo) {

        for (int i = 0; i < cantidadAnimales; i++) { //Solo recorre los espacios ocupados, no los 100

            if (animales[i].isActivo()
                    && animales[i].getCodigo().equalsIgnoreCase(codigo)) { //Si el animal esta activo y el codigo coincide devuelve ese animal

                return animales[i];
            }
        }

        return null;
    }

    public void listarAnimales() {

        System.out.println("\n=== ANIMALES REGISTRADOS ===");

        for (int i = 0; i < cantidadAnimales; i++) {

            if (animales[i].isActivo()) { //Muestra solo los activos

                System.out.println(
                        animales[i].getCodigo() + " | "
                        + animales[i].getNombre() + " | "
                        + animales[i].getEspecie() + " | "
                        + animales[i].getEdad() + " | "
                        + animales[i].getEstado()
                );
            }
        }
    }
}