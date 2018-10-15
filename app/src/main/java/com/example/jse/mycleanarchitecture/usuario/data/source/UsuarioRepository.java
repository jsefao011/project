package com.example.jse.mycleanarchitecture.usuario.data.source;

import com.example.jse.mycleanarchitecture.usuario.data.source.local.UsuarioLocalDataSource;
import com.example.jse.mycleanarchitecture.usuario.data.source.remote.UsuarioRemoteDataSource;
import com.example.jse.mycleanarchitecture.usuario.entities.UsuarioUi;

import java.util.List;

public class UsuarioRepository implements  UsuarioDataSource{

    private UsuarioLocalDataSource usuarioLocalDataSource;
    private UsuarioRemoteDataSource usuarioRemoteDataSource;
    private static UsuarioRepository INSTANCE;

    public static  UsuarioRepository getINSTANCE(UsuarioLocalDataSource usuarioLocalDataSource, UsuarioRemoteDataSource usuarioRemoteDataSource){
        if (INSTANCE==null){
            INSTANCE= new UsuarioRepository(usuarioLocalDataSource, usuarioRemoteDataSource);
        }
        return  INSTANCE;
    }


    private UsuarioRepository(UsuarioLocalDataSource usuarioLocalDataSource, UsuarioRemoteDataSource usuarioRemoteDataSource) {
        this.usuarioLocalDataSource = usuarioLocalDataSource;
        this.usuarioRemoteDataSource = usuarioRemoteDataSource;
    }

    @Override
    public void guardarUsuario(UsuarioUi usuarioUi, Callback<UsuarioUi> callback) {
        usuarioLocalDataSource.guardarUsuario(usuarioUi, callback);
        usuarioRemoteDataSource.guardarUsuario(usuarioUi,callback);
    }

    @Override
    public void listarUsuario(Callback<List<UsuarioUi>> callback) {
        usuarioLocalDataSource.listarUsuario(callback);
    }

    @Override
    public void eliminarUsuario(UsuarioUi usuarioUi, Callback<UsuarioUi> callback) {
        usuarioLocalDataSource.eliminarUsuario(usuarioUi, callback);
        usuarioRemoteDataSource.eliminarUsuario(usuarioUi, callback);
    }

    @Override
    public void editarUsuario(UsuarioUi usuarioUi, Callback<UsuarioUi> callback) {
        usuarioLocalDataSource.editarUsuario(usuarioUi, callback);
        usuarioRemoteDataSource.editarUsuario(usuarioUi, callback);
    }

    @Override
    public void getUsuario(UsuarioUi usuarioUi, Callback<UsuarioUi> callback) {
        usuarioLocalDataSource.getUsuario(usuarioUi, callback);
        usuarioRemoteDataSource.getUsuario(usuarioUi, callback);
    }


}
