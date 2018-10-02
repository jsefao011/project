package com.example.jse.mycleanarchitecture.model.dbflow;

import com.example.jse.mycleanarchitecture.model.dbflow.config.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Jse on 01/10/2018.
 */
@Table(database = AppDatabase.class)
public class Persona extends BaseModel {

    @PrimaryKey
    private int personaId;
    @Column
    private String nombres;
    @Column
    private String apellidos;
    @Column
    private String celular;
    @Column
    private boolean estado;

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
