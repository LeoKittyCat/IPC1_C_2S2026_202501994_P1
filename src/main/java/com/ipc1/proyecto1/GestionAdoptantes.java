package com.ipc1.proyecto1;

public class GestionAdoptantes {

    private Adoptante[] adoptantes;
    private int cantidadAdoptantes;

    public GestionAdoptantes() {
        adoptantes = new Adoptante[100]; // Arreglo estatico con espacio para 100 adoptantes
        cantidadAdoptantes = 0; // Al inicio no tenemos ningun adoptante registrado
    }

    // Registrar Adoptante
    public boolean registrarAdoptante(Adoptante nuevoAdoptante) {

        // Primero comprobamos que todavia haya espacio en el arreglo
        if (cantidadAdoptantes >= adoptantes.length) {
            return false;
        }

        // Recorremos solamente las posiciones que ya tienen adoptantes
        for (int i = 0; i < cantidadAdoptantes; i++) {

            if (adoptantes[i].getCodigo()
                    .equalsIgnoreCase(nuevoAdoptante.getCodigo())) {

                // Evitamos codigos duplicados.
                // Por ejemplo AD001 y ad001 cuentan como el mismo codigo.
                return false;
            }
        }

        // Guardamos el nuevo adoptante en la siguiente posicion disponible
        adoptantes[cantidadAdoptantes] = nuevoAdoptante;
        cantidadAdoptantes++;

        return true;
    }


    public Adoptante buscarPorCodigo(String codigo) {

        for (int i = 0; i < cantidadAdoptantes; i++) {

            if (adoptantes[i].isActivo()
                    && adoptantes[i].getCodigo().equalsIgnoreCase(codigo)) {

                // Si esta activo y el codigo coincide,
                // devolvemos el objeto Adoptante encontrado.
                return adoptantes[i];
            }
        }

        return null; // Si no encontramos al adoptante devolvemos null
    }


    public void buscarPorNombre(String nombre) {

        boolean encontrado = false;

        for (int i = 0; i < cantidadAdoptantes; i++) {

            if (adoptantes[i].isActivo()
                    && adoptantes[i].getNombre().equalsIgnoreCase(nombre)) {

                mostrarAdoptante(adoptantes[i]);
                encontrado = true;
            }
        }

        // Pueden existir varias personas con el mismo nombre,
        // por eso recorremos todo el arreglo y no nos detenemos en la primera.
        if (!encontrado) {
            System.out.println("No se encontraron adoptantes con ese nombre.");
        }
    }


    public boolean editarAdoptante(
            String codigo,
            String nuevoNombre,
            String nuevoTelefono,
            String nuevoCorreo) {

        // Reutilizamos el metodo de busqueda que ya tenemos
        Adoptante adoptante = buscarPorCodigo(codigo);

        if (adoptante != null) {

            // Modificamos el mismo objeto que ya esta guardado en el arreglo (esta en adoptante.java)
            adoptante.setNombre(nuevoNombre);
            adoptante.setTelefono(nuevoTelefono);
            adoptante.setCorreo(nuevoCorreo);

            return true;
        }

        return false;
    }


    public boolean eliminarAdoptante(String codigo) {

        Adoptante adoptante = buscarPorCodigo(codigo);

        if (adoptante != null) {

            // Eliminacion logica:
            // NO borramos al adoptante del arreglo.
            // Simplemente lo marcamos como inactivo.
            adoptante.setActivo(false);

            return true;
        }

        return false;
    }


    public void listarAdoptantes() {

        System.out.println("\n=== ADOPTANTES REGISTRADOS ===");

        boolean hayAdoptantes = false;

        for (int i = 0; i < cantidadAdoptantes; i++) {

            if (adoptantes[i].isActivo()) {

                mostrarAdoptante(adoptantes[i]);
                hayAdoptantes = true;
            }
        }

        if (!hayAdoptantes) {
            System.out.println("No hay adoptantes activos registrados.");
        }
    }


    public void mostrarAdoptante(Adoptante adoptante) {

        // Metodo auxiliar para evitar repetir este mismo
        // System.out.println en las busquedas y listados

        System.out.println(
                adoptante.getCodigo() + " | "
                + adoptante.getNombre() + " | "
                + adoptante.getTelefono() + " | "
                + adoptante.getCorreo()
        );
    }
}