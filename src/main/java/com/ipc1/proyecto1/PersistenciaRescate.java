package com.ipc1.proyecto1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenciaRescate {

    private static final String CARPETA = "data";
    private static final String ARCHIVO = "data/rescates.txt";


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

            // Si rescates.txt no existe lo crea
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

        } catch (IOException e) {

            System.out.println("Error al preparar el archivo de rescates");
        }
    }


    // =========================
    // GUARDAR RESCATES
    // =========================

    public static void guardarRescates(GestionRescate gestion) {

        prepararArchivo();

        try (
            BufferedWriter escritor =
                    new BufferedWriter(new FileWriter(ARCHIVO))
        ) {

            Rescate[] rescates = gestion.getRescates();
            int cantidad = gestion.getCantidadRescates();

            // Recorre solamente las posiciones usadas del arreglo
            for (int i = 0; i < cantidad; i++) {

                Rescate rescate = rescates[i];

                // Guarda todos los datos separados por ;
                escritor.write(
                        rescate.getCodigo() + ";"
                        + rescate.getDescripcion() + ";"
                        + rescate.getUbicacion() + ";"
                        + rescate.getPrioridad() + ";"
                        + rescate.getEstado()
                );

                // Guarda el siguiente rescate en otra linea
                escritor.newLine();
            }

        } catch (IOException e) {

            System.out.println("Error al guardar los rescates");
        }
    }


    // =========================
    // CARGAR RESCATES
    // =========================

    public static void cargarRescates(GestionRescate gestion) {

        prepararArchivo();

        try (
            BufferedReader lector =
                    new BufferedReader(new FileReader(ARCHIVO))
        ) {

            String linea;

            // Lee el archivo hasta que ya no queden lineas
            while ((linea = lector.readLine()) != null) {

                // Separa cada dato usando ;
                String[] datos = linea.split(";");

                if (datos.length == 5) {

                    String codigo = datos[0];
                    String descripcion = datos[1];
                    String ubicacion = datos[2];
                    String prioridad = datos[3];
                    String estado = datos[4];

                    // Vuelve a crear el rescate con los datos del archivo
                    Rescate rescate =
                            new Rescate(
                                    codigo,
                                    descripcion,
                                    ubicacion,
                                    prioridad
                            );

                    // Recupera el estado que tenia antes de cerrar
                    rescate.setEstado(estado);

                    // Vuelve a guardar el rescate dentro del arreglo
                    gestion.registrarRescate(rescate);
                }
            }

        } catch (IOException e) {

            System.out.println("Error al cargar los rescates");
        }
    }
}