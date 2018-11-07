package com.example.jse.mycleanarchitecture.usuario.ui;

import android.os.Bundle;
import android.support.constraint.Group;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jse.mycleanarchitecture.R;
import com.example.jse.mycleanarchitecture.base.UseCaseHandler;
import com.example.jse.mycleanarchitecture.base.UseCaseThreadPoolScheduler;
import com.example.jse.mycleanarchitecture.model.dbflow.Persona;
import com.example.jse.mycleanarchitecture.usuario.UsuarioPresenter;
import com.example.jse.mycleanarchitecture.usuario.UsuarioPresenterImpl;
import com.example.jse.mycleanarchitecture.usuario.data.source.UsuarioRepository;
import com.example.jse.mycleanarchitecture.usuario.data.source.local.UsuarioLocalDataSource;
import com.example.jse.mycleanarchitecture.usuario.data.source.remote.UsuarioRemoteDataSource;
import com.example.jse.mycleanarchitecture.usuario.domain.usecase.DeletePersona;
import com.example.jse.mycleanarchitecture.usuario.domain.usecase.GetPersonas;
import com.example.jse.mycleanarchitecture.usuario.domain.usecase.GetUsuario;
import com.example.jse.mycleanarchitecture.usuario.domain.usecase.SavePersona;
import com.example.jse.mycleanarchitecture.usuario.domain.usecase.UpdatePersona;
import com.example.jse.mycleanarchitecture.usuario.entities.PersonUi;
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
    @BindView(R.id.inp_usuario)
    TextInputEditText inpUsuario;
    @BindView(R.id.inp_password)
    TextInputEditText inpPassword;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.btn_guardar)
    Button btnGuardar;
    @BindView(R.id.btn_cancelar)
    Button btnCancelar;
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
        usuarioAdapter = new UsuarioAdapter(new ArrayList<UsuarioUi>(), this);
        usuarioAdapter.setRecyclerView(rcUsuario);
        rcUsuario.setAdapter(usuarioAdapter);
        rcUsuario.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setupPresenter() {
        usuarioPresenter = new UsuarioPresenterImpl(new UseCaseHandler(new UseCaseThreadPoolScheduler()),
                new SavePersona(UsuarioRepository.getINSTANCE(
                        new UsuarioLocalDataSource(),
                        new UsuarioRemoteDataSource()
                )),
                new GetPersonas(UsuarioRepository.getINSTANCE(
                        new UsuarioLocalDataSource(),
                        new UsuarioRemoteDataSource()
                )),
                new DeletePersona(UsuarioRepository.getINSTANCE(
                                new UsuarioLocalDataSource(),
                                new UsuarioRemoteDataSource()
                )),
                new UpdatePersona(UsuarioRepository.getINSTANCE(
                        new UsuarioLocalDataSource(),
                        new UsuarioRemoteDataSource()
                )),
                new GetUsuario(UsuarioRepository.getINSTANCE(
                        new UsuarioLocalDataSource(),
                        new UsuarioRemoteDataSource()
                )));
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
        usuarioPresenter.onClickUsuario(usuarioUi);
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
    public void setNombreUSuario(String nombreUSuario) {
        inpUsuario.setText(nombreUSuario);
    }

    @Override
    public void setPassword(String password) {
        inpPassword.setText(password);
    }

    @Override
    public void showMenssage(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    @Override
    public void removeItemUser(UsuarioUi usuarioUi) {
        usuarioAdapter.remove(usuarioUi);
    }

    @Override
    public void onClickUsuario(UsuarioUi usuarioUi) {
        usuarioPresenter.onClickUsuario(usuarioUi);
    }

    @Override
    public void onClickRemoveUsuario(UsuarioUi usuarioUi) {
        usuarioPresenter.onClickRemoveUsuario(usuarioUi);
        usuarioAdapter.remove(usuarioUi);
    }



    @Override
    public void onCLickTraerUsuario(UsuarioUi usuarioUi) {
        usuarioPresenter.onClickGetUsuario(usuarioUi);
        onClickUsuario(usuarioUi);
    }

    @OnClick(R.id.floatingActionButton)
    public void onViewClicked() {
        usuarioPresenter.onClickCrearUsuario();
        /**usuarioPresenter.onClickSaveUsuario(inpTextNombre.getText().toString(),
         inpTextApellido.getText().toString(), inpTelefono.getText().toString(),
         inpUsuario.getText().toString(),
         inpPassword.getText().toString()
         );*/
    }

    @OnClick({R.id.btn_guardar, R.id.btn_cancelar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_guardar:
                usuarioPresenter.onClickSaveUsuario(inpTextNombre.getText().toString(),
                        inpTextApellido.getText().toString(), inpTelefono.getText().toString(),
                        inpUsuario.getText().toString(),
                        inpPassword.getText().toString());
                break;

            case R.id.btn_cancelar:
                usuarioPresenter.onClickCancelarUsuario();
                break;
        }
    }
}
