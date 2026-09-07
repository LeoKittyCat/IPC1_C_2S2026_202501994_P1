package com.ipc1.proyecto1;

public class GestionAnimales {

    private Animal[] animales;
    private int cantidadAnimales;

    public GestionAnimales() {
        animales = new Animal[100]; // Creamos un arreglo estatico con espacio maximo para 100 animales
        cantidadAnimales = 0; // Al inicio no tenemos ningun animal registrado
    }

    public boolean registrarAnimal(Animal nuevoAnimal) {

        if (cantidadAnimales >= animales.length) { // Evitamos registrar mas de 100 animales
            return false;
        }

        for (int i = 0; i < cantidadAnimales; i++) {
            // No recorremos los 100 espacios, solo los que ya estan ocupados

            if (animales[i].getCodigo().equalsIgnoreCase(nuevoAnimal.getCodigo())) {
                // equalsIgnoreCase evita codigos duplicados como A001 y a001
                return false;
            }
        }

        animales[cantidadAnimales] = nuevoAnimal; // Guardamos el nuevo animal en la siguiente posicion disponible
        cantidadAnimales++; // Aumentamos la cantidad de animales registrados

        return true;
    }


    public Animal buscarPorCodigo(String codigo) {

        for (int i = 0; i < cantidadAnimales; i++) {

            if (animales[i].isActivo()
                    && animales[i].getCodigo().equalsIgnoreCase(codigo)) {

                // Si el animal sigue activo y el codigo coincide, devolvemos ese objeto Animal
                return animales[i];
            }
        }

        return null; // Si no encontramos nada devolvemos null
    }


    public void buscarPorNombre(String nombre) {

        boolean encontrado = false;

        for (int i = 0; i < cantidadAnimales; i++) {

            if (animales[i].isActivo()
                    && animales[i].getNombre().equalsIgnoreCase(nombre)) {

                mostrarAnimal(animales[i]);
                encontrado = true;
            }
        }

        // A diferencia del codigo, pueden existir varios animales con el mismo nombre
        if (!encontrado) {
            System.out.println("No se encontraron animales con ese nombre.");
        }
    }


    public void buscarPorEspecie(String especie) {

        boolean encontrado = false;

        for (int i = 0; i < cantidadAnimales; i++) {

            if (animales[i].isActivo()
                    && animales[i].getEspecie().equalsIgnoreCase(especie)) {

                mostrarAnimal(animales[i]);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron animales de esa especie.");
        }
    }


    public void buscarPorEstado(String estado) {

        boolean encontrado = false;

        for (int i = 0; i < cantidadAnimales; i++) {

            if (animales[i].isActivo()
                    && animales[i].getEstado().equalsIgnoreCase(estado)) {

                mostrarAnimal(animales[i]);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron animales con ese estado.");
        }
    }

    // Editar estado de los animales
    public boolean editarEstado(String codigo, String nuevoEstado) {

        // Revisamos que el estado a asignar sea valido
        if (!estadoValido(nuevoEstado)){
            return false;
        }
        
        Animal animal = buscarPorCodigo(codigo);
        //Reutilizamos nuestro metodo de busqueda para encontrar al animal
        
        if (animal!= null){
            
            animal.setEstado(nuevoEstado);
            //Modificamos el estado del mismo objeto que ya esta dentro del arreglo
            return false;
        }
        return false;
    }


    public boolean eliminarAnimal(String codigo) {

        Animal animal = buscarPorCodigo(codigo);

        if (animal != null) {animal.setActivo(false);
            // Eliminacion LOGICA:
            // El objeto sigue dentro del arreglo, pero activo pasa a false
            // Por eso ya no aparecera en busquedas ni listados normales

            return true;
        }

        return false;
    }


    public void listarAnimales() {

        System.out.println("\n=== ANIMALES REGISTRADOS ===");

        boolean hayAnimales = false;

        for (int i = 0; i < cantidadAnimales; i++) {

            if (animales[i].isActivo()) {

                mostrarAnimal(animales[i]);
                hayAnimales = true;
            }
        }

        if (!hayAnimales) {
            System.out.println("No hay animales activos registrados.");
        }
    }

    // Mostrar animales
    public void mostrarAnimal(Animal animal) {

        // Metodo auxiliar para no repetir el mismo System.out.println
        // cada vez que queremos mostrar los datos de un animal

        System.out.println(
                animal.getCodigo() + " | "
                + animal.getNombre() + " | "
                + animal.getEspecie() + " | "
                + animal.getEdad() + " | "
                + animal.getEstado()
        );
    }
        // Estados de los animales
    public boolean estadoValido(String estado){
        
        // Vamos a manejra estos cuatro estados ya que el documento no especifica
        
        return estado.equalsIgnoreCase("Ingresado")
                || estado.equalsIgnoreCase("En tratamiento")
                || estado.equalsIgnoreCase("Disponible")
                || estado.equalsIgnoreCase("Adoptado");
                
    }
} //fin del metodo
