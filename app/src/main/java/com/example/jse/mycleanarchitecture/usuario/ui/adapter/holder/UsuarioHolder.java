package com.example.jse.mycleanarchitecture.usuario.ui.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jse.mycleanarchitecture.R;
import com.example.jse.mycleanarchitecture.usuario.entities.PersonUi;
import com.example.jse.mycleanarchitecture.usuario.entities.UsuarioUi;
import com.example.jse.mycleanarchitecture.usuario.listener.UsuarioListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jse on 02/10/2018.
 */

public class UsuarioHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private UsuarioUi usuarioUi;
    private UsuarioListener listener;

    @BindView(R.id.img_perfil)
    ImageView imgPerfil;
    @BindView(R.id.text_number)
    TextView textNumber;
    @BindView(R.id.text_description)
    TextView textDescription;
    @BindView(R.id.text_nombre)
    TextView textNombre;
    @BindView(R.id.img_delete)
    ImageView imgDelete;

    public UsuarioHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        imgDelete.setOnClickListener(this);
        itemView.setOnClickListener(this);
    }

    public void bind(UsuarioUi usuarioUi, UsuarioListener listener){
        this.usuarioUi = usuarioUi;
        this.listener = listener;
        PersonUi personUi = usuarioUi.getPersonUi();
        textNumber.setText(String.valueOf(usuarioUi.getNumero()));
        textNombre.setText(personUi.getNombre());
        textDescription.setText(personUi.getApellido());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_delete:
                listener.onClickRemoveUsuario(usuarioUi);
                break;
                default:
                    listener.onClickUsuario(usuarioUi);
                    break;
        }
    }
}
