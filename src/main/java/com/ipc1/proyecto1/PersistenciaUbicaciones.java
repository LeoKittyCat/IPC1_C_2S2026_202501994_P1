package com.ipc1.proyecto1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenciaUbicaciones {

    private static final String CARPETA = "data";
    private static final String ARCHIVO = "data/ubicaciones.txt";


    // =========================
    // PREPARAR ARCHIVO
    // =========================

    public static void prepararArchivo() {

        File carpeta = new File(CARPETA);

        // Si la carpeta data no existe la crea
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        File archivo = new File(ARCHIVO);

        try {

            // Si ubicaciones.txt no existe lo crea
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

        } catch (IOException e) {

            System.out.println("Error al preparar el archivo de ubicaciones");
        }
    }


    // =========================
    // GUARDAR UBICACIONES
    // =========================

    public static void guardarUbicaciones(
            GestionUbicaciones gestion) {

        prepararArchivo();

        try (
            BufferedWriter escritor =
                    new BufferedWriter(new FileWriter(ARCHIVO))
        ) {

            EspacioRefugio[][] espacios =
                    gestion.getEspacios();


            // Recorre todas las filas de la matriz
            for (int fila = 0;
                    fila < espacios.length;
                    fila++) {


                // Recorre todas las columnas de cada fila
                for (int columna = 0;
                        columna < espacios[fila].length;
                        columna++) {


                    EspacioRefugio espacio =
                            espacios[fila][columna];


                    // Solo guarda las posiciones que tienen un animal
                    if (!espacio.estaDisponible()) {

                        escritor.write(
                                fila + ";"
                                + columna + ";"
                                + espacio.getAnimal().getCodigo()
                        );

                        escritor.newLine();
                    }
                }
            }

        } catch (IOException e) {

            System.out.println("Error al guardar las ubicaciones");
        }
    }


    // =========================
    // CARGAR UBICACIONES
    // =========================

    public static void cargarUbicaciones(
            GestionUbicaciones gestionUbicaciones,
            GestionAnimales gestionAnimales) {

        prepararArchivo();

        try (
            BufferedReader lector =
                    new BufferedReader(new FileReader(ARCHIVO))
        ) {

            String linea;


            // Lee todas las posiciones guardadas
            while ((linea = lector.readLine()) != null) {

                String[] datos =
                        linea.split(";");


                // Cada posicion necesita fila, columna y codigo del animal
                if (datos.length == 3) {

                    int fila =
                            Integer.parseInt(datos[0]);

                    int columna =
                            Integer.parseInt(datos[1]);

                    String codigoAnimal =
                            datos[2];


                    // Busca el mismo objeto Animal que ya fue cargado desde animales.txt
                    Animal animal =
                            gestionAnimales.buscarPorCodigo(
                                    codigoAnimal
                            );


                    // Solo intenta asignar si el animal todavia existe
                    if (animal != null) {

                        gestionUbicaciones.asignarAnimal(
                                fila,
                                columna,
                                animal
                        );
                    }
                }
            }

        } catch (IOException e) {

            System.out.println("Error al cargar las ubicaciones");

        } catch (NumberFormatException e) {

            System.out.println("Hay datos invalidos en ubicaciones.txt");
        }
    }
}