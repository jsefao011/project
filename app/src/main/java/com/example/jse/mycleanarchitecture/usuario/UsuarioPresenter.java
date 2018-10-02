package com.example.jse.mycleanarchitecture.usuario;


import com.example.jse.mycleanarchitecture.base.BasePresenter;
import com.example.jse.mycleanarchitecture.usuario.entities.UsuarioUi;
import com.example.jse.mycleanarchitecture.usuario.ui.UsuarioView;

/**
 * Created by Jse on 01/10/2018.
 */

public interface UsuarioPresenter extends BasePresenter<UsuarioView> {
    void onClickCrearPersona();
    void onClickUsuario(UsuarioUi usuarioUi);
    void onClickRemoveUsuario(UsuarioUi usuarioUi);
}
