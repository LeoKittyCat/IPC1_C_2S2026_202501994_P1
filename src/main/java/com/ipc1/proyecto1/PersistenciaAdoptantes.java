package com.ipc1.proyecto1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenciaAdoptantes {

    // Usa una ruta relativa para que funcione en cualquier computadora
    private static final String CARPETA = "data";
    private static final String ARCHIVO = "data/adoptantes.txt";


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

            // Si adoptantes.txt no existe lo crea
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

        } catch (IOException e) {

            System.out.println("Error al preparar el archivo de adoptantes");
        }
    }


    // =========================
    // GUARDAR ADOPTANTES
    // =========================

    public static void guardarAdoptantes(GestionAdoptantes gestion) {

        prepararArchivo();

        try (
            // BufferedWriter permite escribir los datos dentro del archivo
            BufferedWriter escritor =
                    new BufferedWriter(new FileWriter(ARCHIVO))
        ) {

            Adoptante[] adoptantes = gestion.getAdoptantes();
            int cantidad = gestion.getCantidadAdoptantes();

            // Solo recorre los espacios usados del arreglo
            for (int i = 0; i < cantidad; i++) {

                Adoptante adoptante = adoptantes[i];

                // Guarda todos los datos separados por ;
                escritor.write(
                        adoptante.getCodigo() + ";"
                        + adoptante.getNombre() + ";"
                        + adoptante.getTelefono() + ";"
                        + adoptante.getCorreo() + ";"
                        + adoptante.isActivo()
                );

                // El siguiente adoptante se guarda en otra linea
                escritor.newLine();
            }

        } catch (IOException e) {

            System.out.println("Error al guardar los adoptantes");
        }
    }


    // =========================
    // CARGAR ADOPTANTES
    // =========================

    public static void cargarAdoptantes(GestionAdoptantes gestion) {

        prepararArchivo();

        try (
            // BufferedReader permite leer el archivo linea por linea
            BufferedReader lector =
                    new BufferedReader(new FileReader(ARCHIVO))
        ) {

            String linea;

            // Sigue leyendo hasta llegar al final del archivo
            while ((linea = lector.readLine()) != null) {

                // Separa los datos usando ;
                String[] datos = linea.split(";");

                // Cada adoptante tiene 5 datos guardados
                if (datos.length == 5) {

                    String codigo = datos[0];
                    String nombre = datos[1];
                    String telefono = datos[2];
                    String correo = datos[3];
                    boolean activo = Boolean.parseBoolean(datos[4]);

                    // Vuelve a crear el objeto con los datos del archivo
                    Adoptante adoptante =
                            new Adoptante(
                                    codigo,
                                    nombre,
                                    telefono,
                                    correo
                            );

                    // Recupera si estaba activo o eliminado
                    adoptante.setActivo(activo);

                    // Vuelve a meter el adoptante en el arreglo
                    gestion.registrarAdoptante(adoptante);
                }
            }

        } catch (IOException e) {

            System.out.println("Error al cargar los adoptantes");
        }
    }
}