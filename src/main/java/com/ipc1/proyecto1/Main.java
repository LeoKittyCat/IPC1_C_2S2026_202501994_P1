package com.ipc1.proyecto1;

import java.util.Scanner;
//Ideas clave: Las clases como Animal representan los datos de una entidad. 
//Las clases como GestionAnimales contienen la lógica para administrar los objetos almacenados 
//en los arreglos estáticos.
public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in); //Leer entrada
        GestionAnimales gestion = new GestionAnimales(); //Esta clase se va a encargar de guardar, buscar, listar, evitar,editar y eliminar animales :S

        int opcion = 0;

        do {

            System.out.println("\n=== CENTRO DE RESCATE ANIMAL ===");
            System.out.println("1. Registrar animal");
            System.out.println("2. Listar animales");
            System.out.println("3. Buscar animal por codigo");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");

            try { //Bloque try-catch, si el usuario devuelve algo que no esta en las opciones muestra el error y repite la accion
                opcion = Integer.parseInt(entrada.nextLine()); //Leemos el texto y lo pasamos a entero

                switch (opcion) {

                    case 1:
                        registrarAnimal(entrada, gestion);
                        break;

                    case 2:
                        gestion.listarAnimales();
                        break;

                    case 3:
                        buscarAnimal(entrada, gestion);
                        break;

                    case 4:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opcion no valida.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero."); //Por si el usuario devuelve "Wachimingo" jaja no sabe leer
            }

        } while (opcion != 4); // Repite hasta que la opcion sea 4 (salir)

        entrada.close();
    }

    public static void registrarAnimal(Scanner entrada, GestionAnimales gestion) {

        System.out.println("\n=== REGISTRAR ANIMAL ===");

        System.out.print("Codigo: ");
        String codigo = entrada.nextLine().trim(); //Leemos toda la linea que escribe el usuario hasta que este presione "Enter". Trim elimina espacios al inicio y final

        System.out.print("Nombre: ");
        String nombre = entrada.nextLine().trim();

        System.out.print("Especie (Perro/Gato): ");
        String especie = entrada.nextLine().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || especie.isEmpty()) {
            System.out.println("Los campos no pueden estar vacios."); //Por si alguno queda vacio
            return;
        }

        if (!especie.equalsIgnoreCase("Perro")
                && !especie.equalsIgnoreCase("Gato")) { //Si no es ni perro ni gato no lo aceptamos

            System.out.println("La especie debe ser Perro o Gato.");
            return;
        }

        try {

            System.out.print("Edad: ");
            int edad = Integer.parseInt(entrada.nextLine()); //Lo leemos como texto, lo pasamos a numero

            if (edad < 0) {
                System.out.println("La edad no puede ser negativa.");
                return;
            }

            Animal nuevoAnimal =
                    new Animal(codigo, nombre, especie, edad);

            boolean registrado =
                    gestion.registrarAnimal(nuevoAnimal); //Registramos el nuevo animal en el gestor

            if (registrado) {
                System.out.println("Animal registrado correctamente.");
            } else {
                System.out.println(
                        "No se pudo registrar. El codigo ya existe "
                        + "o no hay espacio disponible."
                );
            }

        } catch (NumberFormatException e) {
            System.out.println("La edad debe ser un numero entero.");
        }
    }

    public static void buscarAnimal(Scanner entrada, GestionAnimales gestion) {

        System.out.println("\n=== BUSCAR ANIMAL ===");

        System.out.print("Ingrese el codigo: ");
        String codigo = entrada.nextLine().trim();

        Animal encontrado = gestion.buscarPorCodigo(codigo); //Buscamos dentro del gestor

        if (encontrado != null) {

            System.out.println("\nAnimal encontrado:");
            System.out.println("Codigo: " + encontrado.getCodigo());
            System.out.println("Nombre: " + encontrado.getNombre());
            System.out.println("Especie: " + encontrado.getEspecie());
            System.out.println("Edad: " + encontrado.getEdad());
            System.out.println("Estado: " + encontrado.getEstado());

        } else {
            System.out.println("No se encontro un animal con ese codigo.");
        }
    }
}