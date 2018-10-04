package com.example.jse.mycleanarchitecture.usuario.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jse.mycleanarchitecture.R;

import com.example.jse.mycleanarchitecture.usuario.entities.UsuarioUi;
import com.example.jse.mycleanarchitecture.usuario.listener.UsuarioListener;
import com.example.jse.mycleanarchitecture.usuario.ui.adapter.holder.UsuarioHolder;

import java.util.List;

/**
 * Created by Jse on 02/10/2018.
 */

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioHolder> {
    private final UsuarioListener listener;
    private List<UsuarioUi> usuarioUiList;
    private RecyclerView recyclerView;

    public UsuarioAdapter(List<UsuarioUi> usuarioUiList, UsuarioListener listener) {
        this.usuarioUiList = usuarioUiList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);
        return new UsuarioHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioHolder holder, int position) {
        UsuarioUi usuarioUi = this.usuarioUiList.get(position);
        holder.bind(usuarioUi,listener);
    }

    @Override
    public int getItemCount() {
        return usuarioUiList.size();
    }

    public void add(UsuarioUi usuarioUi) {
        this.usuarioUiList.add(usuarioUi);
        notifyItemInserted(getItemCount() - 1);
        recyclerView.scrollToPosition(getItemCount() - 1);
    }

    public int update(UsuarioUi usuarioUi) {
        int position = this.usuarioUiList.indexOf(usuarioUi);
        if (position != -1) {
            this.usuarioUiList.set(position, usuarioUi);
            notifyItemChanged(position);
        }
        return position;
    }

    public void remove(UsuarioUi usuarioUi) {
        int position = this.usuarioUiList.indexOf(usuarioUi);
        if (position != -1) {
            this.usuarioUiList.remove(usuarioUi);
            notifyItemRemoved(position);
        }

    }

    public void setUsuarioUiList(List<UsuarioUi> usuarioUiList) {
        this.usuarioUiList.clear();
        this.usuarioUiList.addAll(usuarioUiList);
        notifyDataSetChanged();
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }
}
