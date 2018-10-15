package com.example.jse.mycleanarchitecture.usuario.data.source.remote;

import com.example.jse.mycleanarchitecture.usuario.data.source.UsuarioDataSource;
import com.example.jse.mycleanarchitecture.usuario.entities.UsuarioUi;

import java.util.List;

public class UsuarioRemoteDataSource implements UsuarioDataSource {
    @Override
    public void guardarUsuario(UsuarioUi usuarioUi, Callback<UsuarioUi> callback) {

    }

    @Override
    public void listarUsuario(Callback<List<UsuarioUi>> callback) {

    }

    @Override
    public void eliminarUsuario(UsuarioUi usuarioUi, Callback<UsuarioUi> callback) {

    }

    @Override
    public void editarUsuario(UsuarioUi usuarioUi, Callback<UsuarioUi> callback) {

    }

    @Override
    public void getUsuario(UsuarioUi usuarioUi, Callback<UsuarioUi> callback) {

    }
}
