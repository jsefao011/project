package com.example.jse.mycleanarchitecture.usuario;

import com.example.jse.mycleanarchitecture.usuario.entities.UsuarioUi;
import com.example.jse.mycleanarchitecture.usuario.ui.UsuarioView;

/**
 * Created by Jse on 01/10/2018.
 */

public class UsuarioPresenterImpl implements UsuarioPresenter {

    private UsuarioView view;

    @Override
    public void attachView(UsuarioView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClickCrearPersona() {

    }

    @Override
    public void onClickUsuario(UsuarioUi usuarioUi) {

    }

    @Override
    public void onClickRemoveUsuario(UsuarioUi usuarioUi) {

    }
}
