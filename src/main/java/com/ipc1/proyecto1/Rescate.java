package com.ipc1.proyecto1;

public class Rescate {

    private String codigo;
    private String descripcion;
    private String ubicacion;
    private String prioridad;
    private String estado;

    public Rescate(
            String codigo,
            String descripcion,
            String ubicacion,
            String prioridad) {

        this.codigo = codigo;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.prioridad = prioridad;

        // Todo rescate nuevo comienza activo/pendiente de atender
        this.estado = "Activo";
    }


    // GETTERS
    // Permiten consultar los datos privados del rescate

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public String getEstado() {
        return estado;
    }


    // SETTERS
    // Permiten modificar datos que pueden cambiar durante el proceso

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}