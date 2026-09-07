package com.ipc1.proyecto1;

import java.util.Scanner;

// Idea clave:
// Las clases como Animal representan los datos de una entidad.
// Las clases como GestionAnimales contienen la logica para administrar
// los objetos almacenados dentro de los arreglos estaticos.

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in); // Permite leer lo que escribe el usuario

        GestionAnimales gestion = new GestionAnimales();
        // Creamos un objeto GestionAnimales.
        // Este objeto sera el encargado de guardar, buscar, listar,
        // editar y eliminar animales dentro del arreglo estatico.

        int opcion = 0;

        do {

            System.out.println("\n=== CENTRO DE RESCATE ANIMAL ===");
            System.out.println("1. Registrar animal");
            System.out.println("2. Listar animales");
            System.out.println("3. Buscar animal");
            System.out.println("4. Editar estado");
            System.out.println("5. Eliminar animal");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");

            try {

                opcion = Integer.parseInt(entrada.nextLine());
                // Leemos primero como texto y luego convertimos a entero.
                // Esto evita problemas que suelen aparecer al mezclar nextInt() con nextLine().

                switch (opcion) {

                    case 1:
                        registrarAnimal(entrada, gestion);
                        break;

                    case 2:
                        gestion.listarAnimales();
                        break;

                    case 3:
                        menuBusqueda(entrada, gestion);
                        break;

                    case 4:
                        editarEstado(entrada, gestion);
                        break;

                    case 5:
                        eliminarAnimal(entrada, gestion);
                        break;

                    case 6:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opcion no valida.");
                        break;
                }

            } catch (NumberFormatException e) {
                // Si el usuario escribe algo como "Wachimingo"
                // en vez de un numero, evitamos que el programa se caiga

                System.out.println("Debe ingresar un numero.");
            }

        } while (opcion != 6); // Se repite hasta seleccionar la opcion 6

        entrada.close();
    }


    public static void registrarAnimal(
            Scanner entrada,
            GestionAnimales gestion) {

        System.out.println("\n=== REGISTRAR ANIMAL ===");

        System.out.print("Codigo: ");
        String codigo = entrada.nextLine().trim();
        // trim() elimina espacios innecesarios al inicio y al final

        System.out.print("Nombre: ");
        String nombre = entrada.nextLine().trim();

        System.out.print("Especie (Perro/Gato): ");
        String especie = entrada.nextLine().trim();

        // Validamos que los campos de texto no esten vacios
        if (codigo.isEmpty() || nombre.isEmpty() || especie.isEmpty()) {
            System.out.println("Los campos no pueden estar vacios.");
            return; // Terminamos este metodo y regresamos al menu
        }

        // El proyecto trabaja especificamente con perros y gatos
        if (!especie.equalsIgnoreCase("Perro")
                && !especie.equalsIgnoreCase("Gato")) {

            System.out.println("La especie debe ser Perro o Gato.");
            return;
        }

        try {

            System.out.print("Edad: ");
            int edad = Integer.parseInt(entrada.nextLine());
            // Leemos la edad como texto y luego la convertimos a entero

            if (edad < 0) {
                System.out.println("La edad no puede ser negativa.");
                return;
            }

            Animal nuevoAnimal =
                    new Animal(codigo, nombre, especie, edad);
            // Creamos un objeto Animal con los datos ingresados

            boolean registrado =
                    gestion.registrarAnimal(nuevoAnimal);
            // Le pedimos a GestionAnimales que intente guardarlo en su arreglo

            if (registrado) {

                System.out.println("Animal registrado correctamente.");

            } else {

                System.out.println(
                        "No se pudo registrar. El codigo ya existe "
                        + "o no hay espacio disponible."
                );
            }

        } catch (NumberFormatException e) {

            // Evitamos que el programa falle si escriben "cinco"
            // o cualquier texto donde deberia ir una edad

            System.out.println("La edad debe ser un numero entero.");
        }
    }


    public static void menuBusqueda(
            Scanner entrada,
            GestionAnimales gestion) {

        System.out.println("\n=== BUSCAR ANIMAL ===");
        System.out.println("1. Buscar por codigo");
        System.out.println("2. Buscar por nombre");
        System.out.println("3. Buscar por especie");
        System.out.println("4. Buscar por estado");
        System.out.print("Seleccione una opcion: ");

        try {

            int opcionBusqueda =
                    Integer.parseInt(entrada.nextLine());

            switch (opcionBusqueda) {

                case 1:
                    buscarAnimalPorCodigo(entrada, gestion);
                    break;

                case 2:

                    System.out.print("Ingrese el nombre: ");
                    String nombre = entrada.nextLine().trim();

                    gestion.buscarPorNombre(nombre);
                    break;

                case 3:

                    System.out.print("Ingrese la especie: ");
                    String especie = entrada.nextLine().trim();

                    gestion.buscarPorEspecie(especie);
                    break;

                case 4:

                    System.out.print("Ingrese el estado: ");
                    String estado = entrada.nextLine().trim();

                    gestion.buscarPorEstado(estado);
                    break;

                default:
                    System.out.println("Opcion de busqueda no valida.");
                    break;
            }

        } catch (NumberFormatException e) {

            System.out.println("Debe ingresar un numero.");
        }
    }


    public static void buscarAnimalPorCodigo(
            Scanner entrada,
            GestionAnimales gestion) {

        System.out.println("\n=== BUSCAR POR CODIGO ===");

        System.out.print("Ingrese el codigo: ");
        String codigo = entrada.nextLine().trim();

        Animal encontrado =
                gestion.buscarPorCodigo(codigo);
        // GestionAnimales devuelve el objeto si existe,
        // o devuelve null si no pudo encontrarlo

        if (encontrado != null) {

            System.out.println("\nAnimal encontrado:");

            System.out.println("Codigo: " + encontrado.getCodigo());
            System.out.println("Nombre: " + encontrado.getNombre());
            System.out.println("Especie: " + encontrado.getEspecie());
            System.out.println("Edad: " + encontrado.getEdad());
            System.out.println("Estado: " + encontrado.getEstado());

        } else {

            System.out.println(
                    "No se encontro un animal con ese codigo."
            );
        }
    }

    // Editar estado del animal 
    public static void editarEstado(
            Scanner entrada,
            GestionAnimales gestion) {

        //Comprobamos los campos vacios 
        System.out.println("\n=== EDITAR ESTADO ===");

        System.out.print("Codigo del animal: ");
        String codigo = entrada.nextLine().trim();

        System.out.print("Nuevo estado: ");
        String nuevoEstado = entrada.nextLine().trim();

        if (codigo.isEmpty() || nuevoEstado.isEmpty()) {

            System.out.println("Los campos no pueden estar vacios.");
            return;
        }
        
        if (!gestion.estadoValido(nuevoEstado)) {
                System.out.println("Estado no valido.");
                System.out.println("Estados permitidos:");
                System.out.println("- Ingresado");
                System.out.println("- En tratamiento");
                System.out.println("- Disponible");
                System.out.println("- Adoptado");

                return;
        }

        boolean editado =
                gestion.editarEstado(codigo, nuevoEstado);

        if (editado) {

            System.out.println("Estado actualizado correctamente.");

        } else {

            System.out.println("Animal no encontrado.");
        }
    }


    public static void eliminarAnimal(
            Scanner entrada,
            GestionAnimales gestion) {

        System.out.println("\n=== ELIMINAR ANIMAL ===");

        System.out.print("Codigo del animal: ");
        String codigo = entrada.nextLine().trim();

        if (codigo.isEmpty()) {

            System.out.println("Debe ingresar un codigo.");
            return;
        }

        boolean eliminado =
                gestion.eliminarAnimal(codigo);

        if (eliminado) {

            System.out.println("Animal eliminado correctamente.");

        } else {

            System.out.println("Animal no encontrado.");
        }
    }
}