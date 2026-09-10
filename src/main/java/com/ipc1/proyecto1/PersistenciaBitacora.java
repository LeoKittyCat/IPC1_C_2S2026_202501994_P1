package com.ipc1.proyecto1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenciaBitacora {

    private static final String CARPETA = "data";
    private static final String ARCHIVO = "data/bitacora.txt";


    //Lo que hace todo esto es: Registrar animal;Se registro el animal A001;admin
    
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

            // Si bitacora.txt no existe lo crea
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error al preparar el archivo de bitacora"
            );
        }
    }


    // =========================
    // GUARDAR BITACORA
    // =========================

    public static void guardarBitacora(
            GestionBitacora gestion) {

        prepararArchivo();


        try (
            BufferedWriter escritor =
                    new BufferedWriter(
                            new FileWriter(ARCHIVO)
                    )
        ) {

            Bitacora[] acciones =
                    gestion.getAcciones();

            int cantidad =
                    gestion.getCantidadAcciones();


            // Recorre solamente las posiciones usadas del arreglo
            for (int i = 0; i < cantidad; i++) {

                Bitacora accion =
                        acciones[i];


                // Guarda accion, detalle y usuario separados por ;
                escritor.write(
                        accion.getAccion() + ";"
                        + accion.getDetalle() + ";"
                        + accion.getUsuario()
                );

                escritor.newLine();
            }


        } catch (IOException e) {

            System.out.println(
                    "Error al guardar la bitacora"
            );
        }
    }


    // =========================
    // CARGAR BITACORA
    // =========================

    public static void cargarBitacora(
            GestionBitacora gestion) {

        prepararArchivo();


        try (
            BufferedReader lector =
                    new BufferedReader(
                            new FileReader(ARCHIVO)
                    )
        ) {

            String linea;


            // Lee una accion por cada linea del archivo
            while ((linea = lector.readLine()) != null) {

                String[] datos =
                        linea.split(";");


                // Cada accion necesita exactamente estos 3 datos
                if (datos.length == 3) {

                    String accion =
                            datos[0];

                    String detalle =
                            datos[1];

                    String usuario =
                            datos[2];


                    // Reconstruye el objeto que estaba guardado en el txt
                    Bitacora nuevaAccion =
                            new Bitacora(
                                    accion,
                                    detalle,
                                    usuario
                            );


                    // Vuelve a meter la accion al arreglo
                    gestion.registrarAccion(
                            nuevaAccion
                    );
                }
            }


        } catch (IOException e) {

            System.out.println(
                    "Error al cargar la bitacora"
            );
        }
    }
}