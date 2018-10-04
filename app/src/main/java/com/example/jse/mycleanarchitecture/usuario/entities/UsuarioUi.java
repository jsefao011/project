package com.example.jse.mycleanarchitecture.usuario.entities;

import java.util.Objects;

/**
 * Created by Jse on 01/10/2018.
 */

public class UsuarioUi {


    private int id;
    private int numero;
    private String nombre;
    private String clave;

    private PersonUi personUi;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public PersonUi getPersonUi() {
        return personUi;
    }

    public void setPersonUi(PersonUi personUi) {
        this.personUi = personUi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioUi usuarioUi = (UsuarioUi) o;
        return id == usuarioUi.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
