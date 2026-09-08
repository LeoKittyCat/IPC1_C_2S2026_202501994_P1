package com.ipc1.proyecto1;

public class Usuario {

    private String usuario;
    private String contrasena;
    private String rol;


    // =========================
    // CONSTRUCTOR
    // =========================

    public Usuario(
            String usuario,
            String contrasena,
            String rol) {

        // Guarda los datos que recibe al crear el usuario
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }


    // =========================
    // GETTERS
    // =========================

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getRol() {
        return rol;
    }


    // =========================
    // SETTERS
    // =========================

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}