package com.example.jse.mycleanarchitecture.usuario;

import com.example.jse.mycleanarchitecture.base.UseCase;
import com.example.jse.mycleanarchitecture.base.UseCaseHandler;
import com.example.jse.mycleanarchitecture.model.dbflow.Persona;
import com.example.jse.mycleanarchitecture.model.dbflow.Usuario;
import com.example.jse.mycleanarchitecture.usuario.domain.usecase.GetPersonas;
import com.example.jse.mycleanarchitecture.usuario.domain.usecase.SavePersona;
import com.example.jse.mycleanarchitecture.usuario.entities.PersonUi;
import com.example.jse.mycleanarchitecture.usuario.entities.UsuarioUi;
import com.example.jse.mycleanarchitecture.usuario.ui.UsuarioView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jse on 01/10/2018.
 */

public class UsuarioPresenterImpl implements UsuarioPresenter {

   private UseCaseHandler useCaseHandler;
   private SavePersona savePersona;
   private GetPersonas getPersonas;

    public UsuarioPresenterImpl(UseCaseHandler useCaseHandler, SavePersona savePersona, GetPersonas getPersonas) {
        this.useCaseHandler = useCaseHandler;
        this.savePersona = savePersona;
        this.getPersonas = getPersonas;
    }

    private UsuarioView view;
    List<UsuarioUi> usuarioUis;



    @Override
    public void attachView(UsuarioView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        usuarioUis= new ArrayList<>();
        getPersonas();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        showUsuario(usuarioUis);
    }

    private void showUsuario(List<UsuarioUi> usuarioUiList) {
        if (view==null)return;
        view.showListaUsuario();
        view.hideFormularioUsuario();
        view.setListaUsuario(usuarioUiList);
    }

    private UsuarioUi addUsuario(int id, String nombre, String apellido ){
        PersonUi personUi =  new PersonUi();
        personUi.setNombre(nombre);
        personUi.setApellido(apellido);
        UsuarioUi usuarioUi = new UsuarioUi();
        usuarioUi.setId(id);
        usuarioUi.setPersonUi(personUi);
        return usuarioUi;

    }

//
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
    public void onClickCrearUsuario() {
        if(isViewNull())return;
        view.showFormularioUsuario();
        view.hideListarUsuario();

    }


    @Override
    public void onClickUsuario(UsuarioUi usuarioUi) {

    }

    @Override
    public void onClickRemoveUsuario(UsuarioUi usuarioUi) {
        int position = usuarioUis.indexOf(usuarioUi);
        if (position!=-1){
            usuarioUis.remove(position);
        }
        if (view!=null)view.removeUsuario(usuarioUi);
    }

    @Override
    public void onClickSaveUsuario(String nombre, String apellido, String telefono, String usuario, String password) {
        UsuarioUi usuarioUi = new UsuarioUi();
        usuarioUi.setNombre(usuario);
        usuarioUi.setClave(password);

        PersonUi personUi = new PersonUi();
        personUi.setNombre(nombre);
        personUi.setApellido(apellido);
        personUi.setTelefono(telefono);

        usuarioUi.setPersonUi(personUi);
        savePersona(usuarioUi);
    }

    private void savePersona(final UsuarioUi usuarioUi) {
           useCaseHandler.execute(savePersona, new SavePersona.Request(usuarioUi),
                   new UseCase.UseCaseCallback<SavePersona.Response>() {
                       @Override
                       public void onSuccess(SavePersona.Response response) {
                           usuarioUis.add(response.getUsuarioUi());
                           showUsuario(usuarioUis);
                       }

                       @Override
                       public void onError() {
                            if(view!=null)view.showMenssage("No guardo el Usuario");
                       }
                   });
    }

    @Override
    public void onClickCancelarUsuario() {
       showUsuario(usuarioUis);
    }

    private boolean isViewNull(){
        if(view==null)return true;
        return false;
    }

    public void getPersonas() {

        useCaseHandler.execute(getPersonas, new GetPersonas.Request(),
                new UseCase.UseCaseCallback<GetPersonas.Response>() {
                    @Override
                    public void onSuccess(GetPersonas.Response response) {
                        usuarioUis.clear();
                        usuarioUis.addAll(response.getUsuarioUiList());
                        showUsuario(usuarioUis);

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
