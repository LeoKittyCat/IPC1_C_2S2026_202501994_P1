package com.ipc1.proyecto1;

import java.util.Scanner;

// Idea clave:
// Main funciona temporalmente como interfaz por terminal.
// Aqui pedimos datos al usuario y llamamos a las clases de Gestion.
// Las clases GestionAnimales y GestionAdoptantes contienen la logica
// y administran sus respectivos arreglos estaticos.

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        GestionAnimales gestionAnimales = new GestionAnimales();
        GestionAdoptantes gestionAdoptantes = new GestionAdoptantes();

        int opcion = 0;

        do {

            System.out.println("\n=== CENTRO DE RESCATE ANIMAL ===");
            System.out.println("1. Gestion de animales");
            System.out.println("2. Gestion de adoptantes");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");

            try {

                opcion = Integer.parseInt(entrada.nextLine()); //Pasamos lo ingresado por el usuario de String a numero int

                switch (opcion) {

                    case 1: //animales
                        menuAnimales(entrada, gestionAnimales);
                        break;

                    case 2: //Adoptantes
                        menuAdoptantes(entrada, gestionAdoptantes);
                        break;

                    case 3: //Salir
                        System.out.println("Saliendo del sistema...");
                        break;

                    default: //No valida
                        System.out.println("Opcion no valida.");
                        break;
                }

            } catch (NumberFormatException e) { //Si agarra un error lo mostramos

                System.out.println("Debe ingresar un numero.");
            }

        } while (opcion != 3); // Repetir mientras sea distinta a 3

        entrada.close();
    }


    // =========================
    // MENU DE ANIMALES
    // =========================

    public static void menuAnimales(Scanner entrada,GestionAnimales gestion) { //Si el usuario selecciono la opcion 1

        int opcion = 0;

        do {

            System.out.println("\n=== GESTION DE ANIMALES ===");
            System.out.println("1. Registrar animal");
            System.out.println("2. Listar animales");
            System.out.println("3. Buscar animal");
            System.out.println("4. Editar estado");
            System.out.println("5. Eliminar animal");
            System.out.println("6. Regresar");
            System.out.print("Seleccione una opcion: ");

            try {

                opcion = Integer.parseInt(entrada.nextLine()); //Pasamos lo ingresado por el usuario de String a numero int

                switch (opcion) {

                    case 1: //Registrar animal
                        registrarAnimal(entrada, gestion);
                        break;

                    case 2: //Listar animales registrados
                        gestion.listarAnimales();
                        break;

                    case 3: //Buscar animales registrados
                        menuBusquedaAnimal(entrada, gestion);
                        break;

                    case 4: //Editar estado del animal
                        editarEstadoAnimal(entrada, gestion);
                        break;

                    case 5: //Eliminar animal
                        eliminarAnimal(entrada, gestion);
                        break;

                    case 6: //Regresar
                        System.out.println("Regresando al menu principal...");
                        break;

                    default: //Salir
                        System.out.println("Opcion no valida.");
                        break;
                }

            } catch (NumberFormatException e) { //Mostrar error

                System.out.println("Debe ingresar un numero.");
            }

        } while (opcion != 6); //Repetir siemore que la opcion sea distinta a 6
    }


    public static void registrarAnimal(Scanner entrada,GestionAnimales gestion) { //Opcion 1 de animales

        System.out.println("\n=== REGISTRAR ANIMAL ===");

        System.out.print("Codigo: ");
        String codigo = entrada.nextLine().trim(); //Pedimos codigo y eliminamos espacios al principio y al final

        System.out.print("Nombre: ");
        String nombre = entrada.nextLine().trim(); //Pedimos nombre del animal y eliminamos espacios al principio y al final

        System.out.print("Especie (Perro/Gato): ");
        String especie = entrada.nextLine().trim(); //Adivina que pide... -_-

        if (codigo.isEmpty()|| nombre.isEmpty()|| especie.isEmpty()) { //Si esta vacio algun campo devuelve el mensaje de error ( || = "OR")

            System.out.println("Los campos no pueden estar vacios.");
            return;
        }

        if (!especie.equalsIgnoreCase("Perro")
                && !especie.equalsIgnoreCase("Gato")) {

            System.out.println("La especie debe ser Perro o Gato.");
            return; //hacemos return para evitar que pase al siguiente paso, obligando al usuario a agregar los datos correctos
        }

        try {

            System.out.print("Edad: ");
            int edad = Integer.parseInt(entrada.nextLine()); //Pedimos la edad en forma de String para luego pasarla a numero

            if (edad < 0) {

                System.out.println("La edad no puede ser negativa."); //Si la edad es un numero negativo O NO ES UN NUMERO da error
                return;
            }

            Animal nuevoAnimal =
                    new Animal(codigo, nombre, especie, edad); //Al completar todo se registra el animal

            boolean registrado =
                    gestion.registrarAnimal(nuevoAnimal);

            if (registrado) {

                System.out.println("Animal registrado correctamente.");

            } else {

                System.out.println(
                        "No se pudo registrar. "
                        + "El codigo ya existe o no hay espacio disponible."
                );
            }

        } catch (NumberFormatException e) { //Por si ponen de edad algo que no es un numero...arriba puse algo que me da pereza borrar XD

            System.out.println("La edad debe ser un numero entero.");
        }
    }

    // Menu buscar animal
    public static void menuBusquedaAnimal(
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
                    Integer.parseInt(entrada.nextLine()); //Leemos la opcion que selecciono el usuario y lo pasamos a numero

            switch (opcionBusqueda) {

                case 1:

                    System.out.print("Codigo: ");
                    String codigo = entrada.nextLine().trim(); 

                    Animal encontrado =
                            gestion.buscarPorCodigo(codigo); //El animal pasa a ser "encontrado"

                    if (encontrado != null) {

                        gestion.mostrarAnimal(encontrado); //Si es distinto a null, se muestra el animal

                    } else {

                        System.out.println("Animal no encontrado.");
                    }

                    break;

                case 2:

                    System.out.print("Nombre: ");
                    gestion.buscarPorNombre(
                            entrada.nextLine().trim()
                    );

                    break;

                case 3:

                    System.out.print("Especie: ");
                    gestion.buscarPorEspecie(
                            entrada.nextLine().trim()
                    );

                    break;

                case 4:

                    System.out.print("Estado: ");
                    gestion.buscarPorEstado(
                            entrada.nextLine().trim()
                    );

                    break;

                default:

                    System.out.println("Opcion no valida.");
                    break;
            }

        } catch (NumberFormatException e) {

            System.out.println("Debe ingresar un numero.");
        }
    }

    //Editar estado animal
    public static void editarEstadoAnimal(Scanner entrada,GestionAnimales gestion) {

        System.out.println("\n=== EDITAR ESTADO ===");

        System.out.print("Codigo del animal: ");
        String codigo = entrada.nextLine().trim(); //Pedimos codigo del animal

        System.out.print("Nuevo estado: ");
        String nuevoEstado = entrada.nextLine().trim(); //Pedimos el nuevo estado

        if (codigo.isEmpty()
                || nuevoEstado.isEmpty()) {

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

        if (gestion.editarEstado(codigo, nuevoEstado)) {

            System.out.println("Estado actualizado correctamente.");

        } else {

            System.out.println("Animal no encontrado.");
        }
    }

    // Eliminar animal
    public static void eliminarAnimal(Scanner entrada,GestionAnimales gestion) {

        System.out.println("\n=== ELIMINAR ANIMAL ===");

        System.out.print("Codigo del animal: ");
        String codigo = entrada.nextLine().trim();

        if (codigo.isEmpty()) {

            System.out.println("Debe ingresar un codigo.");
            return;
        }

        if (gestion.eliminarAnimal(codigo)) {

            System.out.println("Animal eliminado correctamente.");

        } else {

            System.out.println("Animal no encontrado.");
        }
    }


    // =========================
    // MENU DE ADOPTANTES
    // =========================

    public static void menuAdoptantes(Scanner entrada,GestionAdoptantes gestion) {

        int opcion = 0;

        do {

            System.out.println("\n=== GESTION DE ADOPTANTES ===");
            System.out.println("1. Registrar adoptante");
            System.out.println("2. Listar adoptantes");
            System.out.println("3. Buscar adoptante");
            System.out.println("4. Editar adoptante");
            System.out.println("5. Regresar");
            System.out.print("Seleccione una opcion: ");

            try {

                opcion = Integer.parseInt(entrada.nextLine());

                switch (opcion) {

                    case 1:
                        registrarAdoptante(entrada, gestion);
                        break;

                    case 2:
                        gestion.listarAdoptantes();
                        break;

                    case 3:
                        buscarAdoptante(entrada, gestion);
                        break;

                    case 4:
                        editarAdoptante(entrada, gestion);
                        break;

                    case 5:
                        System.out.println("Regresando al menu principal...");
                        break;

                    default:
                        System.out.println("Opcion no valida.");
                        break;
                }

            } catch (NumberFormatException e) {

                System.out.println("Debe ingresar un numero.");
            }

        } while (opcion != 5);
    }

    // Registrar adoptante
    public static void registrarAdoptante(
            Scanner entrada,
            GestionAdoptantes gestion) {

        System.out.println("\n=== REGISTRAR ADOPTANTE ===");

        System.out.print("Codigo: ");
        String codigo = entrada.nextLine().trim();

        System.out.print("Nombre: ");
        String nombre = entrada.nextLine().trim();

        System.out.print("Telefono: ");
        String telefono = entrada.nextLine().trim();

        System.out.print("Correo: ");
        String correo = entrada.nextLine().trim();

        if (codigo.isEmpty()
                || nombre.isEmpty()
                || telefono.isEmpty()
                || correo.isEmpty()) {

            System.out.println("Los campos no pueden estar vacios.");
            return;
        }

        Adoptante nuevoAdoptante =
                new Adoptante(
                        codigo,
                        nombre,
                        telefono,
                        correo
                );

        boolean registrado =
                gestion.registrarAdoptante(nuevoAdoptante);

        if (registrado) {

            System.out.println("Adoptante registrado correctamente.");

        } else {

            System.out.println(
                    "No se pudo registrar. "
                    + "El codigo ya existe o no hay espacio disponible."
            );
        }
    }


    public static void buscarAdoptante(
            Scanner entrada,
            GestionAdoptantes gestion) {

        System.out.println("\n=== BUSCAR ADOPTANTE ===");
        System.out.println("1. Buscar por codigo");
        System.out.println("2. Buscar por nombre");
        System.out.print("Seleccione una opcion: ");

        try {

            int opcion =
                    Integer.parseInt(entrada.nextLine());

            switch (opcion) {

                case 1:

                    System.out.print("Codigo: ");
                    String codigo =
                            entrada.nextLine().trim();

                    Adoptante encontrado =
                            gestion.buscarPorCodigo(codigo);

                    if (encontrado != null) {

                        gestion.mostrarAdoptante(encontrado);

                    } else {

                        System.out.println("Adoptante no encontrado.");
                    }

                    break;

                case 2:

                    System.out.print("Nombre: ");

                    gestion.buscarPorNombre(
                            entrada.nextLine().trim()
                    );

                    break;

                default:

                    System.out.println("Opcion no valida.");
                    break;
            }

        } catch (NumberFormatException e) {

            System.out.println("Debe ingresar un numero.");
        }
    }


    public static void editarAdoptante(
            Scanner entrada,
            GestionAdoptantes gestion) {

        System.out.println("\n=== EDITAR ADOPTANTE ===");

        System.out.print("Codigo del adoptante: ");
        String codigo = entrada.nextLine().trim();

        System.out.print("Nuevo nombre: ");
        String nombre = entrada.nextLine().trim();

        System.out.print("Nuevo telefono: ");
        String telefono = entrada.nextLine().trim();

        System.out.print("Nuevo correo: ");
        String correo = entrada.nextLine().trim();

        if (codigo.isEmpty()
                || nombre.isEmpty()
                || telefono.isEmpty()
                || correo.isEmpty()) {

            System.out.println("Los campos no pueden estar vacios.");
            return;
        }

        boolean editado =
                gestion.editarAdoptante(
                        codigo,
                        nombre,
                        telefono,
                        correo
                );

        if (editado) {

            System.out.println("Adoptante editado correctamente.");

        } else {

            System.out.println("Adoptante no encontrado.");
        }
    }
}