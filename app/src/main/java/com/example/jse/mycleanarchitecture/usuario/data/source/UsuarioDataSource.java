package com.example.jse.mycleanarchitecture.usuario.data.source;

import com.example.jse.mycleanarchitecture.usuario.entities.UsuarioUi;

import java.util.List;

public interface UsuarioDataSource {

    interface Callback<T>{
        void onLoad(boolean success, T item);
    }

    void guardarUsuario(UsuarioUi usuarioUi, Callback<UsuarioUi> callback);

    void listarUsuario(Callback<List<UsuarioUi>> callback);
}
