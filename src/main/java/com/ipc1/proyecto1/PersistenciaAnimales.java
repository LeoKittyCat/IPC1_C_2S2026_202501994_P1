package com.ipc1.proyecto1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter; // Esto sirve para crear animales.txt si no existe
import java.io.IOException;

public class PersistenciaAnimales {

    // Ruta relativa para que funcione aunque el proyecto cambie de computadora
    private static final String CARPETA = "data"; //Crea carpetas
    private static final String ARCHIVO = "data/animales.txt";  //Crea el .txt con la info...bueno no


    // =========================
    // PREPARAR ARCHIVO
    // =========================

    public static void prepararArchivo() {

        // Representa la carpeta data, la crea
        File carpeta = new File(CARPETA);

        // Si la carpeta todavia no existe la crea
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        // Representa el archivo donde se van a guardar los animales
        File archivo = new File(ARCHIVO);

        try {

            // Si animales.txt no existe lo crea
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

        } catch (IOException e) {

            // IOException aparece si Java tiene un problema al trabajar con el archivo
            System.out.println("Error al preparar el archivo de animales");
        }
    }


    // =========================
    // GUARDAR ANIMALES
    // =========================

    public static void guardarAnimales(GestionAnimales gestion) {

        prepararArchivo();

        try (
            // FileWriter abre el archivo y BufferedWriter permite escribir en el
            BufferedWriter escritor =
                    new BufferedWriter(new FileWriter(ARCHIVO)) //Aqui SI crea el archivo xd
        ) {

            Animal[] animales = gestion.getAnimales();
            int cantidad = gestion.getCantidadAnimales();

            // Recorre solamente las posiciones usadas del arreglo
            for (int i = 0; i < cantidad; i++) {

                Animal animal = animales[i];

                // Guarda cada dato separado por ;
                // Tambien guarda activo para conservar la eliminacion logica
                // Guarda cada animal y su codigo y especie y aaaaaa!
                escritor.write(
                        animal.getCodigo() + ";"
                        + animal.getNombre() + ";"
                        + animal.getEspecie() + ";"
                        + animal.getEdad() + ";"
                        + animal.getEstado() + ";"
                        + animal.isActivo()
                );

                // Hace que el siguiente animal se guarde en otra linea
                escritor.newLine();
            }

        } catch (IOException e) {

            System.out.println("Error al guardar los animales");
        }
    }


    // =========================
    // CARGAR ANIMALES
    // =========================

    public static void cargarAnimales(GestionAnimales gestion) {

        prepararArchivo();

        try (
            // FileReader abre el archivo y BufferedReader permite leerlo linea por linea
            BufferedReader lector =
                    new BufferedReader(new FileReader(ARCHIVO))
        ) {

            String linea;

            // readLine devuelve null cuando ya no quedan lineas por leer
            while ((linea = lector.readLine()) != null) {

                // Separa la linea cada vez que encuentra ;
                String[] datos = linea.split(";");

                // Cada animal tiene que tener sus 6 datos
                if (datos.length == 6) {

                    String codigo = datos[0];
                    String nombre = datos[1];
                    String especie = datos[2];
                    int edad = Integer.parseInt(datos[3]);
                    String estado = datos[4];
                    boolean activo = Boolean.parseBoolean(datos[5]);

                    // Crea otra vez el objeto usando los datos guardados
                    Animal animal =
                            new Animal(
                                    codigo,
                                    nombre,
                                    especie,
                                    edad
                            );

                    // Recupera el estado que tenia antes de cerrar el programa
                    animal.setEstado(estado);

                    // Recupera si estaba activo o eliminado
                    animal.setActivo(activo);

                    // Lo vuelve a meter al arreglo de GestionAnimales
                    gestion.registrarAnimal(animal);
                }
            }

        } catch (IOException e) {

            System.out.println("Error al cargar los animales");

        } catch (NumberFormatException e) {

            // Evita que el programa se caiga si la edad guardada esta dañada
            System.out.println("Hay datos invalidos en el archivo de animales");
        }
    }
}


// Pa que se me quede: new File("data"); crea una carpeta
// new FileWriter("data/animales.txt") abre o crea el archivo
//esccritor.write(...) escribe los datos