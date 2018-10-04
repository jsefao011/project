package com.example.jse.mycleanarchitecture.usuario.entities;

import java.lang.ref.SoftReference;

/**
 * Created by Jse on 01/10/2018.
 */

public class PersonUi {

    private int id;
    private String nombre;
    private String apellido;
    private String telefono;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
