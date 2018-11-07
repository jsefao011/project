package com.example.jse.mycleanarchitecture.usuario;

import com.example.jse.mycleanarchitecture.base.UseCase;
import com.example.jse.mycleanarchitecture.base.UseCaseHandler;
import com.example.jse.mycleanarchitecture.usuario.domain.usecase.DeletePersona;
import com.example.jse.mycleanarchitecture.usuario.domain.usecase.GetPersonas;
import com.example.jse.mycleanarchitecture.usuario.domain.usecase.GetUsuario;
import com.example.jse.mycleanarchitecture.usuario.domain.usecase.SavePersona;
import com.example.jse.mycleanarchitecture.usuario.domain.usecase.UpdatePersona;
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
   private  DeletePersona deletePersona;
   private UpdatePersona updatePersona;
   private GetUsuario getUsuario;
   private UsuarioUi usuarioUi;

    public UsuarioPresenterImpl(UseCaseHandler useCaseHandler, SavePersona savePersona, GetPersonas getPersonas, DeletePersona deletePersona, UpdatePersona updatePersona, GetUsuario getUsuario) {
        this.useCaseHandler = useCaseHandler;
        this.savePersona = savePersona;
        this.getPersonas = getPersonas;
        this.deletePersona = deletePersona;
        this.updatePersona = updatePersona;
        this.getUsuario = getUsuario;
    }

    private UsuarioView view;
    List<UsuarioUi> usuarioUiList;



    @Override
    public void attachView(UsuarioView view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        usuarioUiList = new ArrayList<>();
        getPersonas();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        showUsuario(usuarioUiList);
    }

    private void showUsuario(List<UsuarioUi> usuarioUiList) {
        if (view==null)return;
        view.showListaUsuario();
        view.hideFormularioUsuario();

        int count = 0;
        for (UsuarioUi usuarioUi: usuarioUiList){
            count++;
            usuarioUi.setNumero(count);
        }
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
        usuarioUi = null;
        view.setNombreUSuario("");
        view.setPassword("");
        view. setNombrePersona("");
        view.setApellidoPersona("");
        view.setTelefonoPersona("");
    }


    @Override
    public void onClickUsuario(UsuarioUi usuarioUi) {
        if(isViewNull())return;
        view.showFormularioUsuario();
        view.hideListarUsuario();
        this.usuarioUi = usuarioUi;
        view.setNombreUSuario(usuarioUi.getNombre());
        view.setPassword(usuarioUi.getClave());
        PersonUi personUi = usuarioUi.getPersonUi();
        view. setNombrePersona(personUi.getNombre());
        view.setApellidoPersona(personUi.getApellido());
        view.setTelefonoPersona(personUi.getTelefono());
    }

    @Override
    public void onClickRemoveUsuario(UsuarioUi usuarioUi) {

        deletePersona(usuarioUi);

        int position = usuarioUiList.indexOf(usuarioUi);
        if (position!=-1){
            usuarioUiList.remove(position);
        }
        if (view!=null)view.removeUsuario(usuarioUi);
    }

    private void deletePersona(final UsuarioUi usuarioUi) {
        useCaseHandler.execute(deletePersona, new DeletePersona.Request(usuarioUi),
                new UseCase.UseCaseCallback<DeletePersona.Response>() {
                    @Override
                    public void onSuccess(DeletePersona.Response response) {
                       if (view!=null)view.removeItemUser(usuarioUi);
                    }

                    @Override
                    public void onError() {
                        if(view!=null)view.showMenssage("No se elimino el Usuario");
                    }
                });
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

        if(this.usuarioUi==null){
            savePersona(usuarioUi);
        }else {
            usuarioUi.setId(this.usuarioUi.getId());
            personUi.setId(this.usuarioUi.getPersonUi().getId());
            updatePersona(usuarioUi);
        }



    }

    private void savePersona(final UsuarioUi usuarioUi) {
        useCaseHandler.execute(savePersona, new SavePersona.Request(usuarioUi),
                new UseCase.UseCaseCallback<SavePersona.Response>() {
                    @Override
                    public void onSuccess(SavePersona.Response response) {
                        getPersonas();
                    }

                    @Override
                    public void onError() {
                        if(view!=null)view.showMenssage("No guardo el Usuario");
                    }
                });
    }

    @Override
    public void onClickGetUsuario(UsuarioUi usuarioUi) {
        getUsuario(usuarioUi);
    }

    private void getUsuario(final UsuarioUi usuarioUi) {

        useCaseHandler.execute(getUsuario, new GetUsuario.Request(usuarioUi),
                new UseCase.UseCaseCallback<GetUsuario.Response>() {
                    @Override
                    public void onSuccess(GetUsuario.Response response) {
                        onClickUsuario(response.getUsuarioUi());
                    }

                    @Override
                    public void onError() {

                    }
                });
        }

    private void updatePersona(final UsuarioUi usuarioUi) {
        useCaseHandler.execute(updatePersona, new UpdatePersona.Request(usuarioUi),
                new UseCase.UseCaseCallback<UpdatePersona.Response>() {
                    @Override
                    public void onSuccess(UpdatePersona.Response response) {
                        getPersonas();
                    }

                    @Override
                    public void onError() {
                        if(view!=null)view.showMenssage("No edito el Usuario");
                    }
                });
    }


    @Override
    public void onClickCancelarUsuario() {
       showUsuario(usuarioUiList);
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
                        usuarioUiList.clear();
                        usuarioUiList.addAll(response.getUsuarioUiList());
                        showUsuario(usuarioUiList);

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
