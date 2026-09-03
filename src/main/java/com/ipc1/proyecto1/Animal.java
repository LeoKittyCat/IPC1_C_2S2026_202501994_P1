package com.ipc1.proyecto1;


public class Animal {
    // Cada que registremos un animal vamos a pedir estos datos para crearlo como objeto.
    private String codigo;
    private String nombre;
    private String especie;
    private String estado;
    private int edad;
    private boolean activo;
    
    public Animal(String codigo, String nombre, String especie, int edad){
        this.codigo = codigo;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.estado = "Ingresado";
        this.activo = true;
    }
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public int getEdad() {
        return edad;
    }

    public String getEstado() {
        return estado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
