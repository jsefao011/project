package com.example.jse.mycleanarchitecture.usuario.ui;

import android.os.Bundle;
import android.support.constraint.Group;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jse.mycleanarchitecture.R;
import com.example.jse.mycleanarchitecture.usuario.UsuarioPresenter;
import com.example.jse.mycleanarchitecture.usuario.UsuarioPresenterImpl;
import com.example.jse.mycleanarchitecture.usuario.entities.UsuarioUi;
import com.example.jse.mycleanarchitecture.usuario.listener.UsuarioListener;
import com.example.jse.mycleanarchitecture.usuario.ui.adapter.UsuarioAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UsuarioActivity extends AppCompatActivity implements UsuarioView, UsuarioListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.inp_text_nombre)
    TextInputEditText inpTextNombre;
    @BindView(R.id.inp_text_apellido)
    TextInputEditText inpTextApellido;
    @BindView(R.id.inp_telefono)
    TextInputEditText inpTelefono;
    @BindView(R.id.rc_usuario)
    RecyclerView rcUsuario;
    @BindView(R.id.grupo_formulario_persona)
    Group grupoFormularioPersona;
    private UsuarioPresenter usuarioPresenter;
    private UsuarioAdapter usuarioAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupPresenter();
        setupAdapterPersona();

    }

    private void setupAdapterPersona() {
        usuarioAdapter = new UsuarioAdapter(new ArrayList<UsuarioUi>(),this);
        usuarioAdapter.setRecyclerView(rcUsuario);
        rcUsuario.setAdapter(usuarioAdapter);
        rcUsuario.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setupPresenter() {
        usuarioPresenter = new UsuarioPresenterImpl();
        setPresenter(usuarioPresenter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void setPresenter(UsuarioPresenter presenter) {
        presenter.attachView(this);
        presenter.onCreate();
    }


    @Override
    protected void onStart() {
        super.onStart();
        usuarioPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        usuarioPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        usuarioPresenter.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        usuarioPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        usuarioPresenter.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        usuarioPresenter.onBackPressed();
    }



    @OnClick(R.id.appbar)
    public void onClickCrearPersona() {
        usuarioPresenter.onClickCrearPersona();
    }


    @Override
    public void showFormularioUsuario() {
        grupoFormularioPersona.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFormularioUsuario() {
        grupoFormularioPersona.setVisibility(View.GONE);
    }

    @Override
    public void showListaUsuario() {
        rcUsuario.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideListarUsuario() {
        rcUsuario.setVisibility(View.GONE);
    }

    @Override
    public void setListaUsuario(List<UsuarioUi> usuarioUiList) {
        usuarioAdapter.setUsuarioUiList(usuarioUiList);
    }

    @Override
    public void updateUsuario(UsuarioUi usuarioUi) {
        usuarioAdapter.update(usuarioUi);
    }

    @Override
    public void addUsuario(UsuarioUi usuarioUi) {
        usuarioAdapter.add(usuarioUi);
    }

    @Override
    public void removeUsuario(UsuarioUi usuarioUi) {
        usuarioAdapter.remove(usuarioUi);
    }

    @Override
    public void setNombrePersona(String nombres) {
        inpTextNombre.setText(nombres);
    }

    @Override
    public void errorNombrePersona(String error) {
        inpTextNombre.setError(error);
    }

    @Override
    public void setApellidoPersona(String apellido) {
        inpTextApellido.setText(apellido);
    }

    @Override
    public void errorApellidoPersona(String error) {
        inpTextApellido.setError(error);
    }

    @Override
    public void setTelefonoPersona(String telefono) {
        inpTelefono.setText(telefono);
    }

    @Override
    public void errorTelefonoPersona(String error) {
        inpTelefono.setError(error);
    }

    @Override
    public void showMenssage(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClickUsuario(UsuarioUi usuarioUi) {
        usuarioPresenter.onClickUsuario(usuarioUi);
    }

    @Override
    public void onClickRemoveUsuario(UsuarioUi usuarioUi) {
        usuarioPresenter.onClickRemoveUsuario(usuarioUi);
    }
}
