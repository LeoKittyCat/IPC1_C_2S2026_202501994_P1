package com.ipc1.proyecto1;

public class GestionSolicitudes {

    private Solicitud[] solicitudes;
    private int cantidadSolicitudes;

    public GestionSolicitudes() {

        // Arreglo estatico con capacidad maxima para 100 solicitudes
        solicitudes = new Solicitud[100];

        // Al comenzar el programa no hay solicitudes registradas
        cantidadSolicitudes = 0;
    }



    // REGISTRAR SOLICITUD
    public boolean registrarSolicitud(Solicitud nuevaSolicitud) {

        // Verificamos primero que todavia exista espacio en el arreglo
        if (cantidadSolicitudes >= solicitudes.length) {
            return false;
        }

        // Recorremos solamente las posiciones ocupadas del arreglo
        for (int i = 0; i < cantidadSolicitudes; i++) {

            // Evitamos que existan dos solicitudes con el mismo codigo
            if (solicitudes[i].getCodigo()
                    .equalsIgnoreCase(nuevaSolicitud.getCodigo())) {

                return false;
            }
        }

        // Guardamos la solicitud en la siguiente posicion disponible
        solicitudes[cantidadSolicitudes] = nuevaSolicitud;

        // Aumentamos nuestro contador de solicitudes
        cantidadSolicitudes++;

        return true;
    }


    // BUSCAR POR CODIGO
    public Solicitud buscarPorCodigo(String codigo) {

        for (int i = 0; i < cantidadSolicitudes; i++) {

            if (solicitudes[i].getCodigo()
                    .equalsIgnoreCase(codigo)) {

                // Si encontramos el codigo devolvemos
                // el objeto Solicitud completo
                return solicitudes[i];
            }
        }

        // null significa que no encontramos ninguna solicitud
        return null;
    }


    // =========================
    // CAMBIAR ESTADO
    // =========================
    public boolean cambiarEstado(
            String codigo,
            String nuevoEstado) {

        // Aunque Main ya valida el estado, tambien protegemos
        // la logica interna del gestor por seguridad.
        if (!estadoValido(nuevoEstado)) {
            return false;
        }

        Solicitud solicitud = buscarPorCodigo(codigo);

        if (solicitud != null) {

            // Modificamos el mismo objeto Solicitud
            // que ya se encuentra dentro del arreglo
            solicitud.setEstado(nuevoEstado);

            return true;
        }

        return false;
    }


    // =========================
    // VALIDAR ESTADO
    // =========================
    public boolean estadoValido(String estado) {

        // Estos estados son una decision de diseño nuestra,
        // ya que el documento no proporciona una lista exacta.

        return estado.equalsIgnoreCase("Pendiente")
                || estado.equalsIgnoreCase("Aprobada")
                || estado.equalsIgnoreCase("Rechazada");
    }


    // =========================
    // LISTAR PENDIENTES
    // =========================
    public void listarPendientes() {

        System.out.println("\n=== SOLICITUDES PENDIENTES ===");

        boolean hayPendientes = false;

        for (int i = 0; i < cantidadSolicitudes; i++) {

            if (solicitudes[i].getEstado()
                    .equalsIgnoreCase("Pendiente")) {

                mostrarSolicitud(solicitudes[i]);

                hayPendientes = true;
            }
        }

        if (!hayPendientes) {
            System.out.println("No hay solicitudes pendientes.");
        }
    }


    // HISTORIAL DE SOLICITUDES
    public void mostrarHistorial() {

        System.out.println("\n=== HISTORIAL DE SOLICITUDES ===");

        if (cantidadSolicitudes == 0) {

            System.out.println("No hay solicitudes registradas.");
            return;
        }

        // El historial muestra todas las solicitudes:
        // pendientes, aprobadas y rechazadas
        for (int i = 0; i < cantidadSolicitudes; i++) {

            mostrarSolicitud(solicitudes[i]);
        }
    }


    // MOSTRAR UNA SOLICITUD
    public void mostrarSolicitud(Solicitud solicitud) {

        // Este metodo auxiliar evita repetir el mismo
        // System.out.println en historial, busquedas y pendientes.

        System.out.println(
                solicitud.getCodigo() + " | "
                + "Adoptante: "
                + solicitud.getAdoptante().getCodigo() + " - "
                + solicitud.getAdoptante().getNombre() + " | "
                + "Animal: "
                + solicitud.getAnimal().getCodigo() + " - "
                + solicitud.getAnimal().getNombre() + " | "
                + "Estado: "
                + solicitud.getEstado()
        );
    }
    
    // Devuelve el arreglo para poder guardarlo en el archivo
    public Solicitud[] getSolicitudes() {
        return solicitudes;
    }

    // Devuelve cuantos espacios del arreglo se estan usando
    public int getCantidadSolicitudes() {
        return cantidadSolicitudes;
    }
    
    //REVISAMOS SI HAY SOLICITUES PEDIENTES
    public int contarPendientesPorAnimal(String codigoAnimal) {

        int cantidad = 0;

        for (int i = 0; i < cantidadSolicitudes; i++) {

            Solicitud solicitud = solicitudes[i];

            if (solicitud.getAnimal().getCodigo().equalsIgnoreCase(codigoAnimal)
                    && solicitud.getEstado().equalsIgnoreCase("Pendiente")) {

                cantidad++;
            }
        }

        return cantidad;
    }
    
    // Este y elo de arriba solo revisa cuantas solicitudes pendientes hay y cuantas
    public int contarPendientesPorAdoptante(String codigoAdoptante) {

        int cantidad = 0;

        for (int i = 0; i < cantidadSolicitudes; i++) {

            Solicitud solicitud = solicitudes[i];

            if (solicitud.getAdoptante().getCodigo().equalsIgnoreCase(codigoAdoptante)
                    && solicitud.getEstado().equalsIgnoreCase("Pendiente")) {

                cantidad++;
            }
        }

        return cantidad;
    }
    
    // Eliminamos las solicitues pendientes
    public void eliminarPendientesPorAnimal(String codigoAnimal) {

        for (int i = 0; i < cantidadSolicitudes; i++) {

            Solicitud solicitud = solicitudes[i];

            if (solicitud.getAnimal().getCodigo().equalsIgnoreCase(codigoAnimal)
                    && solicitud.getEstado().equalsIgnoreCase("Pendiente")) {

                for (int j = i; j < cantidadSolicitudes - 1; j++) {
                    solicitudes[j] = solicitudes[j + 1];
                }

                solicitudes[cantidadSolicitudes - 1] = null;
                cantidadSolicitudes--;

                // Regresa una posicion porque el arreglo se movio
                i--; //Osea, eliminamos al puesto 0, asi que el siguiente en la fila
                     //Pasa a ser el puesto 0 y asi...
            }
            
            
        }
    }
    
    public void eliminarPendientesPorAdoptante(String codigoAdoptante) {

        for (int i = 0; i < cantidadSolicitudes; i++) {

            Solicitud solicitud = solicitudes[i];

            if (solicitud.getAdoptante().getCodigo().equalsIgnoreCase(codigoAdoptante)
                    && solicitud.getEstado().equalsIgnoreCase("Pendiente")) {

                for (int j = i; j < cantidadSolicitudes - 1; j++) {
                    solicitudes[j] = solicitudes[j + 1];
                }

                solicitudes[cantidadSolicitudes - 1] = null;
                cantidadSolicitudes--;

                i--;
            }
        }
    }
    
    public boolean existePendiente(String codigoAdoptante, String codigoAnimal) {

        for (int i = 0; i < cantidadSolicitudes; i++) {
        //pasa por todas las solicitudes que tenemos
            

            Solicitud solicitud = solicitudes[i];

            if (solicitud.getAdoptante().getCodigo()
                    .equalsIgnoreCase(codigoAdoptante)
                    && solicitud.getAnimal().getCodigo()
                    .equalsIgnoreCase(codigoAnimal)
                    && solicitud.getEstado()
                    .equalsIgnoreCase("Pendiente")) {
                //Si encuentra dos o mas identicas y en pendiente devuelve true

                return true;
            }
        }

        return false;
    }
}