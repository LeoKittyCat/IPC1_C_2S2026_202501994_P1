package com.ipc1.proyecto1;

//Importamos Java Input/Output (java.io)
import java.io.BufferedReader; //Permite leer linea por linea
import java.io.BufferedWriter; //Facilita la escritura 
import java.io.File; //Representa una carpeta o archivo y permite comprobar si existe o crearlo
import java.io.FileReader; //Abre un archivo para leerlo
import java.io.FileWriter; //Abre un archivo para escribir en el 
import java.io.IOException; //Excepcion que puede pasar al trabajar con archivos

public class PersistenciaUsuarios {

    private static final String CARPETA = "data";
    private static final String ARCHIVO = "data/usuarios.txt";


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

            // Si usuarios.txt no existe lo crea
            if (!archivo.exists()) {

                archivo.createNewFile();

                // Si el archivo acaba de ser creado agrega
                // un Administrador y un Auxiliar para poder iniciar sesion
                crearUsuariosIniciales();
            }

        } catch (IOException e) {

            System.out.println("Error al preparar el archivo de usuarios");
        }
    }


    // =========================
    // CREAR USUARIOS INICIALES
    // =========================

    public static void crearUsuariosIniciales() {

        try (
            BufferedWriter escritor =
                    new BufferedWriter(new FileWriter(ARCHIVO))
        ) {

            // Estos usuarios se guardan en el archivo
            // Main no tiene las credenciales escritas directamente
            escritor.write("admin;admin123;Administrador");
            escritor.newLine();

            escritor.write("auxiliar;aux123;Auxiliar");
            escritor.newLine();

        } catch (IOException e) {

            System.out.println("Error al crear los usuarios iniciales");
        }
    }


    // =========================
    // CARGAR USUARIOS
    // =========================

    public static void cargarUsuarios(GestionUsuarios gestion) {

        prepararArchivo();

        try (
            BufferedReader lector =
                    new BufferedReader(new FileReader(ARCHIVO))
        ) {

            String linea;

            // Lee todos los usuarios guardados en el archivo
            while ((linea = lector.readLine()) != null) {

                // Cada usuario guarda nombre, contraseña y rol
                String[] datos = linea.split(";");

                if (datos.length == 3) {

                    String usuario = datos[0];
                    String contrasena = datos[1];
                    String rol = datos[2];

                    Usuario nuevoUsuario =
                            new Usuario(
                                    usuario,
                                    contrasena,
                                    rol
                            );

                    // Mete el usuario cargado dentro del arreglo estatico
                    gestion.registrarUsuario(nuevoUsuario);
                }
            }

        } catch (IOException e) {

            System.out.println("Error al cargar los usuarios");
        }
    }
}