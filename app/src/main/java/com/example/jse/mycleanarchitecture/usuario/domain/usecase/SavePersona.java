package com.example.jse.mycleanarchitecture.usuario.domain.usecase;

import com.example.jse.mycleanarchitecture.base.UseCase;
import com.example.jse.mycleanarchitecture.usuario.data.source.UsuarioDataSource;
import com.example.jse.mycleanarchitecture.usuario.data.source.UsuarioRepository;
import com.example.jse.mycleanarchitecture.usuario.entities.UsuarioUi;

/**
 * Created by Jse on 02/10/2018.
 */

public class SavePersona extends UseCase<SavePersona.Request, SavePersona.Response>{


    private UsuarioRepository usuarioRepository;

    public SavePersona(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void executeUseCase(Request requestValues) {
            usuarioRepository.guardarUsuario(requestValues.getUsuarioUi(), new UsuarioDataSource.Callback<UsuarioUi>() {
                @Override
                public void onLoad(boolean success, UsuarioUi item) {
                    if (success) {
                        getUseCaseCallback().onSuccess(new Response(item));
                    }else {
                        getUseCaseCallback().onError();
                    }
                }
            });
    }



    public static class Response implements UseCase.ResponseValue{
        private UsuarioUi usuarioUi;

        public Response(UsuarioUi usuarioUi) {
            this.usuarioUi = usuarioUi;
        }

        public UsuarioUi getUsuarioUi() {
            return usuarioUi;
        }
    }

    public static class Request implements UseCase.RequestValues{
        private UsuarioUi usuarioUi;

        public Request(UsuarioUi usuarioUi) {
            this.usuarioUi = usuarioUi;
        }

        public UsuarioUi getUsuarioUi() {
            return usuarioUi;
        }
    }
}
