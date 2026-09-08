package com.ipc1.proyecto1;

public class GestionRescate {

    private Rescate[] rescates;
    private int cantidadRescates;

    public GestionRescate() {

        // Arreglo estatico con capacidad maxima para 100 rescates
        // No uso ArrayList porque el proyecto pide trabajar
        // con arreglos estaticos...
        // Que bueno por que ni me acuerdo que eran jejeje
        rescates = new Rescate[100];

        // Al iniciar el programa todavia no tenemos rescates registrados.
        cantidadRescates = 0;
    }


    // =========================
    // REGISTRAR RESCATE
    // =========================
    public boolean registrarRescate(Rescate nuevoRescate) {

        // Primero verificamos que todavia exista espacio
        // disponible dentro de nuestro arreglo
        if (cantidadRescates >= rescates.length) {
            return false;
        }

        // Recorremos solamente las posiciones que ya estan ocupadas.
        for (int i = 0; i < cantidadRescates; i++) {

            // No permitimos dos rescates con el mismo codigo.
            // equalsIgnoreCase hace que R001 y r001 sean considerados iguales.
            if (rescates[i].getCodigo()
                    .equalsIgnoreCase(nuevoRescate.getCodigo())) {

                return false;
            }
        }

        // Guardamos el rescate en la siguiente posicion disponible.
        rescates[cantidadRescates] = nuevoRescate;

        // Aumentamos el contador porque ahora tenemos un rescate mas.
        cantidadRescates++;

        return true;
    }


    // =========================
    // BUSCAR RESCATE POR CODIGO
    // =========================
    public Rescate buscarPorCodigo(String codigo) {

        for (int i = 0; i < cantidadRescates; i++) {

            if (rescates[i].getCodigo()
                    .equalsIgnoreCase(codigo)) {

                // Si encontramos el codigo, devolvemos
                // el objeto Rescate completo.
                return rescates[i];
            }
        }

        // Si terminamos de recorrer y no encontramos nada,
        // devolvemos null.
        return null;
    }


    // =========================
    // VALIDAR PRIORIDAD
    // =========================
    public boolean prioridadValida(String prioridad) {

        // El documento exige asignar prioridades, pero no indica nombrs ni na'
        return prioridad.equalsIgnoreCase("Baja")
                || prioridad.equalsIgnoreCase("Media")
                || prioridad.equalsIgnoreCase("Alta");
    }


    // =========================
    // CAMBIAR PRIORIDAD
    // =========================
    public boolean cambiarPrioridad(
            String codigo,
            String nuevaPrioridad) {

        // Protegemos la logica de prioridad dentro del gestor
        if (!prioridadValida(nuevaPrioridad)) {
            return false;
        }

        // Esto busca el rescate utilizando el metodo
        // que ya creamos anteriormente
        Rescate rescate = buscarPorCodigo(codigo);

        if (rescate != null) {

            // Modificamos el mismo objeto que ya se encuentra
            // almacenado dentro del arreglo
            rescate.setPrioridad(nuevaPrioridad);

            return true;
        }

        return false;
    }


    // =========================
    // ATENDER RESCATE
    // =========================
    public boolean atenderRescate(String codigo) {

        //Buscamos dentro del arreglo el codigo ingresado
        Rescate rescate = buscarPorCodigo(codigo);

        if (rescate != null) {

            // No borramos el rescate
            // Simplemente cambiamos su estado para indicar
            // que el caso ya fue atendido
            //En vez de hacerlonull
            
            //Aqui se cambia de activo a atendido
            rescate.setEstado("Atendido");
            

            return true;
        }

        return false;
    }


    // =========================
    // LISTAR RESCATES ACTIVOS
    // =========================
    public void listarActivos() {

        System.out.println("\n=== RESCATES ACTIVOS ===");

        boolean hayActivos = false;

        for (int i = 0; i < cantidadRescates; i++) {

            // Solamente mostramos los casos que todavia
            // se encuentran pendientes de ser atendidos
            if (rescates[i].getEstado()
                    .equalsIgnoreCase("Activo")) {

                mostrarRescate(rescates[i]);
                hayActivos = true;
            }
        }

        if (!hayActivos) {
            System.out.println("No hay rescates activos.");
        }
    }


    // =========================
    // MOSTRAR HISTORIAL
    // =========================
    public void mostrarHistorial() {

        System.out.println("\n=== HISTORIAL DE RESCATES ===");

        if (cantidadRescates == 0) {

            System.out.println("No hay rescates registrados.");
            return;
        }

        // Aqui mostramos TODOS los rescates registrados,
        // tanto los activos como los que ya fueron atendidos
        for (int i = 0; i < cantidadRescates; i++) {

            mostrarRescate(rescates[i]);
        }
    }


    // =========================
    // MOSTRAR UN RESCATE
    // =========================
    public void mostrarRescate(Rescate rescate) {

        // Metodo auxiliar para no repetir el mismo
        // System.out.println en listados y futuras busquedas

        System.out.println(
                rescate.getCodigo() + " | "
                + "Descripcion: " + rescate.getDescripcion() + " | "
                + "Ubicacion: " + rescate.getUbicacion() + " | "
                + "Prioridad: " + rescate.getPrioridad() + " | "
                + "Estado: " + rescate.getEstado()
        );
    }
}