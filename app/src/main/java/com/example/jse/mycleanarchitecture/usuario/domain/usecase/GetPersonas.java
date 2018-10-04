package com.example.jse.mycleanarchitecture.usuario.domain.usecase;

import com.example.jse.mycleanarchitecture.base.UseCase;
import com.example.jse.mycleanarchitecture.model.dbflow.Usuario;
import com.example.jse.mycleanarchitecture.usuario.data.source.UsuarioDataSource;
import com.example.jse.mycleanarchitecture.usuario.data.source.UsuarioRepository;
import com.example.jse.mycleanarchitecture.usuario.entities.UsuarioUi;
import com.raizlabs.android.dbflow.list.IFlowCursorIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jse on 02/10/2018.
 */

public class GetPersonas extends UseCase<GetPersonas.Request, GetPersonas.Response> {

    private UsuarioRepository usuarioRepository;

    public GetPersonas(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void executeUseCase(Request requestValues) {
        usuarioRepository.listarUsuario(new UsuarioDataSource.Callback<List<UsuarioUi>>() {
            @Override
            public void onLoad(boolean success, List<UsuarioUi> item) {
                if (success){

                    List<UsuarioUi>usuarioUiList = new ArrayList<>();
                    int count= 0;
                    for (UsuarioUi itemUsuario:item) {
                        count++;
                        itemUsuario.setNumero(count);
                        usuarioUiList.add(itemUsuario);

                    }

                    getUseCaseCallback().onSuccess(new Response(usuarioUiList));
                }else {
                    getUseCaseCallback().onError();
                }
            }
        });
    }

    public static class Request implements UseCase.RequestValues {

    }

    public static class Response implements UseCase.ResponseValue {
        private List<UsuarioUi> usuarioUiList;

        public Response(List<UsuarioUi> usuarioUiList) {
            this.usuarioUiList = usuarioUiList;
        }

        public List<UsuarioUi> getUsuarioUiList() {
            return usuarioUiList;
        }
    }

}
