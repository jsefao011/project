package com.example.jse.mycleanarchitecture.usuario;


import com.example.jse.mycleanarchitecture.base.BasePresenter;
import com.example.jse.mycleanarchitecture.model.dbflow.Usuario;
import com.example.jse.mycleanarchitecture.usuario.entities.UsuarioUi;
import com.example.jse.mycleanarchitecture.usuario.ui.UsuarioView;

/**
 * Created by Jse on 01/10/2018.
 */

public interface UsuarioPresenter extends BasePresenter<UsuarioView> {
    void onClickCrearUsuario();
    void onClickUsuario(UsuarioUi usuarioUi);
    void onClickRemoveUsuario(UsuarioUi usuarioUi);
    void onClickSaveUsuario(String nombre, String apellido, String telefono, String usuario, String password);
    void onClickUpdate(String nombre, String apellido, String telefono, String usuario, String password);
    void onClickGetUsuario(UsuarioUi usuarioUi);

    void onClickCancelarUsuario();
}

