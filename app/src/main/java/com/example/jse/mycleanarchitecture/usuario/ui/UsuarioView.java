package com.example.jse.mycleanarchitecture.usuario.ui;

import com.example.jse.mycleanarchitecture.base.BaseView;
import com.example.jse.mycleanarchitecture.usuario.UsuarioPresenter;
import com.example.jse.mycleanarchitecture.usuario.entities.UsuarioUi;

import java.util.List;

/**
 * Created by Jse on 01/10/2018.
 */

public interface UsuarioView extends BaseView<UsuarioPresenter> {
    void showFormularioUsuario();
    void hideFormularioUsuario();
    
    void showListaUsuario();
    void hideListarUsuario();
    void setListaUsuario(List<UsuarioUi> usuarioUiList);
    void updateUsuario(UsuarioUi usuarioUi);
    void addUsuario(UsuarioUi usuarioUi);
    void removeUsuario(UsuarioUi usuarioUi);
    
    void setNombrePersona(String nombres);
    void errorNombrePersona(String error);
    void setApellidoPersona(String apellido);
    void errorApellidoPersona(String error);
    void setTelefonoPersona(String telefono);
    void errorTelefonoPersona(String error);
    
    void showMenssage(String mensaje);
}
