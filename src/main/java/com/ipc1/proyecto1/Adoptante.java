
package com.ipc1.proyecto1;


public class Adoptante {
    
    // pResenta los datos de un adoptante
    
    private String codigo;
    private String nombre;
    private String telefono;
    private String correo;
    private boolean activo;
    
    public Adoptante(String codigo, String nombre, String telefono, String correo) {
        
        // Guardamos los datos del adoptante creado
        this.codigo = codigo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.activo = true; //Al registrarse, el adoptante pasa a ser activo
    }
    
    // GETTERS:
    // Nos permiten consultar los datos privados del adoptante
    
    public String getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public String getTelefono() {
        return telefono;
    }
    public String getCorreo() {
        return correo;
    }
    public boolean isActivo() {
        return activo;
    }
    
    // Setters:
    // Nos permiten cambiar los datos modificables
    
    public void setNombre(String nombre){
        this.nombre = nombre; // reescribimos lo antes registrado
    }
    
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
    public void setCorreo (String correo){
        this.correo = correo;
    }
    public void setActivo (boolean activo){
        this.activo = activo;
    }
}
