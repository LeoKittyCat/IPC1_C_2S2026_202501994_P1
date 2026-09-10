//POR SI PREGUNTA, ESTO NO LEE LOS .TXT 
//ESTA TOMANDO LOS DATOS DE LOS GESTORES QUE YA ESTAN CARGADOS EN MEMORIA
//CONSTRUYE EL HTML EN BASE A LOS DATOS DENTRO DEL .TXT

package com.ipc1.proyecto1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GeneradorReportes {

    // Todos los HTML se van a guardar dentro de esta carpeta
    private static final String CARPETA = "reportes";


    // =========================
    // PREPARAR CARPETA
    // =========================

    private static void prepararCarpeta() {

        File carpeta = new File(CARPETA);

        // Si la carpeta reportes no existe la crea automaticamente
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }


    // =========================
    // INICIO DEL HTML
    // =========================

    private static String inicioHTML(String titulo) {

        return "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>" + titulo + "</title>\n"
                + "<style>\n"
                + "body { font-family: Arial; margin: 40px; }\n"
                + "h1 { text-align: center; }\n"
                + "table { border-collapse: collapse; width: 100%; }\n"
                + "th, td { border: 1px solid black; padding: 8px; text-align: center; }\n"
                + "th { background-color: #dddddd; }\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>" + titulo + "</h1>\n";
    }


    // =========================
    // FINAL DEL HTML
    // =========================

    private static String finHTML() {

        return "</body>\n"
                + "</html>";
    }


    // =========================
    // REPORTE DE ANIMALES
    // =========================

    public static boolean generarReporteAnimales(
            GestionAnimales gestion) {

        prepararCarpeta();
        //Toma la carpeta, sin rutas ni na
        String archivo =
                CARPETA + "/reporte_animales.html";


        try (
            BufferedWriter escritor =
                    new BufferedWriter(
                            new FileWriter(archivo)
                    )
        ) {

            escritor.write(
                    inicioHTML("Reporte de Animales")
            );


            escritor.write(
                    "<table>\n"
                    + "<tr>\n"
                    + "<th>Codigo</th>\n"
                    + "<th>Nombre</th>\n"
                    + "<th>Especie</th>\n"
                    + "<th>Edad</th>\n"
                    + "<th>Estado</th>\n"
                    + "</tr>\n"
            );

            // Obtiene el arreglo que ya tenemos en memoria
            Animal[] animales =
                    gestion.getAnimales();

            int cantidad =
                    gestion.getCantidadAnimales();


            // Recorre solamente las posiciones usadas del arreglo
            for (int i = 0; i < cantidad; i++) {

                Animal animal = animales[i];

                //Convierte el dato en celda html
                escritor.write(
                        "<tr>\n"
                        + "<td>" + animal.getCodigo() + "</td>\n"
                        + "<td>" + animal.getNombre() + "</td>\n"
                        + "<td>" + animal.getEspecie() + "</td>\n"
                        + "<td>" + animal.getEdad() + "</td>\n"
                        + "<td>" + animal.getEstado() + "</td>\n"
                        + "</tr>\n"
                );
            }


            escritor.write("</table>\n");

            escritor.write(finHTML());

            return true;


        } catch (IOException e) {

            System.out.println(
                    "Error al generar el reporte de animales"
            );

            return false;
        }
    }


    // =========================
    // REPORTE DE ADOPTANTES
    // =========================

    public static boolean generarReporteAdoptantes(
            GestionAdoptantes gestion) {

        prepararCarpeta();

        String archivo =
                CARPETA + "/reporte_adoptantes.html";


        try (
            BufferedWriter escritor =
                    new BufferedWriter(
                            new FileWriter(archivo)
                    )
        ) {

            escritor.write(
                    inicioHTML("Reporte de Adoptantes")
            );


            escritor.write(
                    "<table>\n"
                    + "<tr>\n"
                    + "<th>Codigo</th>\n"
                    + "<th>Nombre</th>\n"
                    + "<th>Telefono</th>\n"
                    + "<th>Correo</th>\n"
                    + "</tr>\n"
            );


            Adoptante[] adoptantes =
                    gestion.getAdoptantes();

            int cantidad =
                    gestion.getCantidadAdoptantes();


            for (int i = 0; i < cantidad; i++) {

                Adoptante adoptante =
                        adoptantes[i];


                // Solo muestra adoptantes que no fueron eliminados logicamente
                if (adoptante.isActivo()) {

                    escritor.write(
                            "<tr>\n"
                            + "<td>" + adoptante.getCodigo() + "</td>\n"
                            + "<td>" + adoptante.getNombre() + "</td>\n"
                            + "<td>" + adoptante.getTelefono() + "</td>\n"
                            + "<td>" + adoptante.getCorreo() + "</td>\n"
                            + "</tr>\n"
                    );
                }
            }


            escritor.write("</table>\n");

            escritor.write(finHTML());

            return true;


        } catch (IOException e) {

            System.out.println(
                    "Error al generar el reporte de adoptantes"
            );

            return false;
        }
    }


    // =========================
    // REPORTE DE SOLICITUDES
    // =========================

    public static boolean generarReporteSolicitudes(
            GestionSolicitudes gestion) {

        prepararCarpeta();

        String archivo =
                CARPETA + "/reporte_solicitudes.html";


        try (
            BufferedWriter escritor =
                    new BufferedWriter(
                            new FileWriter(archivo)
                    )
        ) {

            escritor.write(
                    inicioHTML("Reporte de Solicitudes")
            );


            escritor.write(
                    "<table>\n"
                    + "<tr>\n"
                    + "<th>Codigo</th>\n"
                    + "<th>Adoptante</th>\n"
                    + "<th>Animal</th>\n"
                    + "<th>Estado</th>\n"
                    + "</tr>\n"
            );


            Solicitud[] solicitudes =
                    gestion.getSolicitudes();

            int cantidad =
                    gestion.getCantidadSolicitudes();


            for (int i = 0; i < cantidad; i++) {

                Solicitud solicitud =
                        solicitudes[i];


                escritor.write(
                        "<tr>\n"
                        + "<td>" + solicitud.getCodigo() + "</td>\n"
                        + "<td>" + solicitud.getAdoptante().getCodigo() + "</td>\n"
                        + "<td>" + solicitud.getAnimal().getCodigo() + "</td>\n"
                        + "<td>" + solicitud.getEstado() + "</td>\n"
                        + "</tr>\n"
                );
            }


            escritor.write("</table>\n");

            escritor.write(finHTML());

            return true;


        } catch (IOException e) {

            System.out.println(
                    "Error al generar el reporte de solicitudes"
            );

            return false;
        }
    }


    // =========================
    // REPORTE DE RESCATES
    // =========================

    public static boolean generarReporteRescates(
            GestionRescate gestion) {

        prepararCarpeta();

        String archivo =
                CARPETA + "/reporte_rescates.html";


        try (
            BufferedWriter escritor =
                    new BufferedWriter(
                            new FileWriter(archivo)
                    )
        ) {

            escritor.write(
                    inicioHTML("Reporte de Rescates")
            );


            escritor.write(
                    "<table>\n"
                    + "<tr>\n"
                    + "<th>Codigo</th>\n"
                    + "<th>Descripcion</th>\n"
                    + "<th>Ubicacion</th>\n"
                    + "<th>Prioridad</th>\n"
                    + "<th>Estado</th>\n"
                    + "</tr>\n"
            );


            Rescate[] rescates =
                    gestion.getRescates();

            int cantidad =
                    gestion.getCantidadRescates();


            for (int i = 0; i < cantidad; i++) {

                Rescate rescate =
                        rescates[i];


                escritor.write(
                        "<tr>\n"
                        + "<td>" + rescate.getCodigo() + "</td>\n"
                        + "<td>" + rescate.getDescripcion() + "</td>\n"
                        + "<td>" + rescate.getUbicacion() + "</td>\n"
                        + "<td>" + rescate.getPrioridad() + "</td>\n"
                        + "<td>" + rescate.getEstado() + "</td>\n"
                        + "</tr>\n"
                );
            }


            escritor.write("</table>\n");

            escritor.write(finHTML());

            return true;


        } catch (IOException e) {

            System.out.println(
                    "Error al generar el reporte de rescates"
            );

            return false;
        }
    }


    // =========================
    // REPORTE DE UBICACIONES
    // =========================

    public static boolean generarReporteUbicaciones(
            GestionUbicaciones gestion) {

        prepararCarpeta();

        String archivo =
                CARPETA + "/reporte_ubicaciones.html";


        try (
            BufferedWriter escritor =
                    new BufferedWriter(
                            new FileWriter(archivo)
                    )
        ) {

            escritor.write(
                    inicioHTML("Reporte de Ubicaciones")
            );


            escritor.write("<table>\n");

            escritor.write(
                    "<tr>"
                    + "<th>Area</th>"
                    + "<th>Espacio 0</th>"
                    + "<th>Espacio 1</th>"
                    + "<th>Espacio 2</th>"
                    + "<th>Espacio 3</th>"
                    + "<th>Espacio 4</th>"
                    + "</tr>\n"
            );


            EspacioRefugio[][] espacios =
                    gestion.getEspacios();


            // Aqui recorremos la matriz igual que en VentanaUbicaciones
            for (int fila = 0;
                    fila < espacios.length;
                    fila++) {


                escritor.write("<tr>");

                escritor.write(
                        "<td>"
                        + gestion.obtenerNombreArea(fila)
                        + "</td>"
                );


                for (int columna = 0;
                        columna < espacios[fila].length;
                        columna++) {


                    EspacioRefugio espacio =
                            espacios[fila][columna];


                    if (espacio.estaDisponible()) {

                        escritor.write(
                                "<td>Libre</td>"
                        );

                    } else {

                        escritor.write(
                                "<td>"
                                + espacio.getAnimal().getCodigo()
                                + "</td>"
                        );
                    }
                }


                escritor.write("</tr>\n");
            }


            escritor.write("</table>\n");

            escritor.write(finHTML());

            return true;


        } catch (IOException e) {

            System.out.println(
                    "Error al generar el reporte de ubicaciones"
            );

            return false;
        }
    }
    
    // =========================
    // REPORTE DE ADOPCIONES
    // =========================

    public static boolean generarReporteAdopciones(
            GestionSolicitudes gestion) {

        prepararCarpeta();

        String archivo =
                CARPETA + "/reporte_adopciones.html";


        try (
            BufferedWriter escritor =
                    new BufferedWriter(
                            new FileWriter(archivo)
                    )
        ) {

            escritor.write(
                    inicioHTML("Reporte de Adopciones")
            );


            escritor.write(
                    "<table>\n"
                    + "<tr>\n"
                    + "<th>Solicitud</th>\n"
                    + "<th>Adoptante</th>\n"
                    + "<th>Animal</th>\n"
                    + "<th>Especie</th>\n"
                    + "<th>Estado</th>\n"
                    + "</tr>\n"
            );


            Solicitud[] solicitudes =
                    gestion.getSolicitudes();

            int cantidad =
                    gestion.getCantidadSolicitudes();


            for (int i = 0; i < cantidad; i++) {

                Solicitud solicitud =
                        solicitudes[i];


                // Una solicitud aprobada representa una adopcion completada
                if (solicitud.getEstado()
                        .equalsIgnoreCase("Aprobada")) {


                    escritor.write(
                            "<tr>\n"
                            + "<td>" + solicitud.getCodigo() + "</td>\n"
                            + "<td>"
                            + solicitud.getAdoptante().getCodigo()
                            + " - "
                            + solicitud.getAdoptante().getNombre()
                            + "</td>\n"
                            + "<td>"
                            + solicitud.getAnimal().getCodigo()
                            + " - "
                            + solicitud.getAnimal().getNombre()
                            + "</td>\n"
                            + "<td>"
                            + solicitud.getAnimal().getEspecie()
                            + "</td>\n"
                            + "<td>Adoptado</td>\n"
                            + "</tr>\n"
                    );
                }
            }


            escritor.write("</table>\n");

            escritor.write(finHTML());

            return true;


        } catch (IOException e) {

            System.out.println(
                    "Error al generar el reporte de adopciones"
            );

            return false;
        }
    }
}