package com.example.jse.mycleanarchitecture.usuario.ui.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jse.mycleanarchitecture.usuario.entities.UsuarioUi;
import com.example.jse.mycleanarchitecture.usuario.listener.UsuarioListener;

/**
 * Created by Jse on 02/10/2018.
 */

public class UsuarioHolder extends RecyclerView.ViewHolder {
    private UsuarioUi usuarioUi;
    private UsuarioListener listener;
    public UsuarioHolder(View itemView) {
        super(itemView);
    }

    public void bind(UsuarioUi usuarioUi, UsuarioListener listener){
        this.usuarioUi = usuarioUi;
        this.listener = listener;
    }


}
