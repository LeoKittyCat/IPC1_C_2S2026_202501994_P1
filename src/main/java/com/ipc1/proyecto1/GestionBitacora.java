package com.ipc1.proyecto1;

public class GestionBitacora {

    private Bitacora[] acciones;
    private int cantidadAcciones;


    public GestionBitacora() {

        // Arreglo estatico para guardar las acciones del sistema
        acciones = new Bitacora[500];

        cantidadAcciones = 0;
    }


    // =========================
    // REGISTRAR ACCION
    // =========================

    public boolean registrarAccion(Bitacora accion) {

        if (cantidadAcciones >= acciones.length) {
            return false;
        }

        acciones[cantidadAcciones] = accion;
        cantidadAcciones++;

        return true;
    }


    // =========================
    // GETTERS
    // =========================

    public Bitacora[] getAcciones() {
        return acciones;
    }

    public int getCantidadAcciones() {
        return cantidadAcciones;
    }
}