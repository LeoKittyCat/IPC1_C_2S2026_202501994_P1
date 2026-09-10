package com.ipc1.proyecto1;

public class Bitacora {

    private String accion;
    private String detalle;
    private String usuario;


    // =========================
    // CONSTRUCTOR
    // =========================

    public Bitacora(
            String accion,
            String detalle,
            String usuario) {

        this.accion = accion;
        this.detalle = detalle;
        this.usuario = usuario;
    }


    // =========================
    // GETTERS
    // =========================

    public String getAccion() {
        return accion;
    }

    public String getDetalle() {
        return detalle;
    }

    public String getUsuario() {
        return usuario;
    }
}