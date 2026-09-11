package com.ipc1.proyecto1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenciaSolicitudes {

    private static final String CARPETA = "data";
    private static final String ARCHIVO = "data/solicitudes.txt";


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

            // Si solicitudes.txt no existe lo crea
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

        } catch (IOException e) {

            System.out.println("Error al preparar el archivo de solicitudes");
        }
    }


    // =========================
    // GUARDAR SOLICITUDES
    // =========================

    public static void guardarSolicitudes(GestionSolicitudes gestion) {

        prepararArchivo();

        try (
            // BufferedWriter permite escribir los datos en el archivo
            BufferedWriter escritor =
                    new BufferedWriter(new FileWriter(ARCHIVO))
        ) {

            Solicitud[] solicitudes = gestion.getSolicitudes();
            int cantidad = gestion.getCantidadSolicitudes();

            // Recorre solamente las posiciones usadas del arreglo
            for (int i = 0; i < cantidad; i++) {

                Solicitud solicitud = solicitudes[i];

                // Guarda los codigos en lugar de copiar objetos completos
                escritor.write(
                        solicitud.getCodigo() + ";"
                        + solicitud.getAdoptante().getCodigo() + ";"
                        + solicitud.getAnimal().getCodigo() + ";"
                        + solicitud.getEstado()
                );

                escritor.newLine();
            }

        } catch (IOException e) {

            System.out.println("Error al guardar las solicitudes");
        }
    }


    // =========================
    // CARGAR SOLICITUDES
    // =========================

    public static void cargarSolicitudes(
            GestionSolicitudes gestionSolicitudes,
            GestionAdoptantes gestionAdoptantes,
            GestionAnimales gestionAnimales) {

        prepararArchivo();

        try (
            BufferedReader lector =
                    new BufferedReader(new FileReader(ARCHIVO))
        ) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                // Separa los datos que fueron guardados con ;
                String[] datos = linea.split(";");

                if (datos.length == 4) {

                    String codigoSolicitud = datos[0];
                    String codigoAdoptante = datos[1];
                    String codigoAnimal = datos[2];
                    String estado = datos[3];


                    // Busca los objetos que ya fueron cargados desde sus archivos
                    Adoptante adoptante =
                            gestionAdoptantes.buscarPorCodigoTodos(codigoAdoptante);

                    Animal animal =
                            gestionAnimales.buscarPorCodigoTodos(codigoAnimal);


                    // Solo recupera la solicitud si ambos objetos existen
                    if (adoptante != null && animal != null) {

                        Solicitud solicitud =
                                new Solicitud(
                                        codigoSolicitud,
                                        adoptante,
                                        animal
                                );

                        // Recupera el estado que tenia antes de cerrar
                        solicitud.setEstado(estado);

                        gestionSolicitudes.registrarSolicitud(solicitud);
                    }
                }
            }

        } catch (IOException e) {

            System.out.println("Error al cargar las solicitudes");
        }
    }
}