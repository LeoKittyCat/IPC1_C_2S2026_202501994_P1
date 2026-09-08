package com.ipc1.proyecto1;

public class GestionUsuarios {

    private Usuario[] usuarios;
    private int cantidadUsuarios;


    // =========================
    // CONSTRUCTOR
    // =========================

    public GestionUsuarios() {

        // Arreglo estatico con espacio para 100 usuarios
        usuarios = new Usuario[100];

        // Al inicio no hay ningun usuario cargado
        cantidadUsuarios = 0;
    }


    // =========================
    // REGISTRAR USUARIO
    // =========================

    public boolean registrarUsuario(Usuario nuevoUsuario) {

        // Revisa que todavia haya espacio en el arreglo
        if (cantidadUsuarios >= usuarios.length) {
            return false;
        }

        // Evita que existan dos usuarios con el mismo nombre
        for (int i = 0; i < cantidadUsuarios; i++) {

            if (usuarios[i].getUsuario()
                    .equalsIgnoreCase(nuevoUsuario.getUsuario())) {

                return false;
            }
        }

        // Guarda el usuario en la siguiente posicion disponible
        usuarios[cantidadUsuarios] = nuevoUsuario;
        cantidadUsuarios++;

        return true;
    }


    // =========================
    // INICIAR SESION
    // =========================

    public Usuario iniciarSesion(
            String nombreUsuario,
            String contrasena) {

        // Recorre los usuarios que fueron cargados desde el archivo
        for (int i = 0; i < cantidadUsuarios; i++) {

            // El usuario puede ignorar mayusculas pero la contraseña no
            if (usuarios[i].getUsuario().equalsIgnoreCase(nombreUsuario)
                    && usuarios[i].getContrasena().equals(contrasena)) {

                // Devuelve el objeto Usuario si las credenciales coinciden
                return usuarios[i];
            }
        }

        // null significa que no encontro credenciales validas
        return null;
    }


    // =========================
    // GETTERS PARA PERSISTENCIA
    // =========================

    public Usuario[] getUsuarios() {

        // Devuelve el arreglo para poder trabajar con sus datos
        return usuarios;
    }

    public int getCantidadUsuarios() {

        // Devuelve cuantos espacios del arreglo estan ocupados
        return cantidadUsuarios;
    }
}