package com.example.jse.mycleanarchitecture.usuario.data.source.local;

import android.util.Log;

import com.example.jse.mycleanarchitecture.model.dbflow.Persona;
import com.example.jse.mycleanarchitecture.model.dbflow.Persona_Table;
import com.example.jse.mycleanarchitecture.model.dbflow.Usuario;
import com.example.jse.mycleanarchitecture.model.dbflow.Usuario_Table;
import com.example.jse.mycleanarchitecture.usuario.data.source.UsuarioDataSource;
import com.example.jse.mycleanarchitecture.usuario.entities.PersonUi;
import com.example.jse.mycleanarchitecture.usuario.entities.UsuarioUi;
import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.List;

public class UsuarioLocalDataSource implements UsuarioDataSource {
    @Override
    public void guardarUsuario(UsuarioUi usuarioUi, Callback<UsuarioUi> callback) {

        try{

            Persona persona = SQLite.select()
                    .from(Persona.class)
                    .orderBy(Persona_Table.personaId.desc())
                    .querySingle();
            int personaId = 1;
            if(persona!=null)personaId = persona.getPersonaId()+1;


            PersonUi personUi = usuarioUi.getPersonUi();

            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombres(personUi.getNombre());
            nuevaPersona.setApellidos(personUi.getApellido());
            nuevaPersona.setCelular(personUi.getTelefono());
            nuevaPersona.setPersonaId(personaId);

            if (!nuevaPersona.save())throw new Error("Error al guardar Persona");


            Usuario usuario = SQLite.select()
                    .from(Usuario.class)
                    .orderBy(Usuario_Table.usuarioId.desc())
                    .querySingle();
            int usuarioId = 1;
            if(usuario!=null)usuarioId = usuario.getUsuarioId()+1;

            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setUsuarioId(usuarioId);
            nuevoUsuario.setUsuario(usuarioUi.getNombre());
            nuevoUsuario.setPassword(usuarioUi.getClave());
            nuevoUsuario.setPersonaId(nuevaPersona.getPersonaId());
            if (!nuevoUsuario.save())throw  new Error("Error al guardar Usuario");

            usuarioUi.setId(usuarioId);
            personUi.setId(personaId);
            callback.onLoad(true,usuarioUi);
        }catch (Exception e){
            e.printStackTrace();
            callback.onLoad(false, null);
        }



    }

    @Override
    public void listarUsuario(Callback<List<UsuarioUi>> callback) {
        List<UsuarioUi>usuarioUiList = new ArrayList<>();

        List<Usuario> usuarioList = SQLite.select()
                .from(Usuario.class)
                .queryList();

        for (Usuario usuario:usuarioList){
            Persona persona = SQLite.select().from(Persona.class)
                    .where(Persona_Table.personaId.eq(usuario.getPersonaId()))
                    .querySingle();
            if (persona==null)continue;

            PersonUi personUi = new PersonUi();
            personUi.setId(persona.getPersonaId());
            personUi.setNombre(persona.getNombres());
            personUi.setApellido(persona.getApellidos());
            personUi.setTelefono(persona.getCelular());

            UsuarioUi usuarioUi = new UsuarioUi();
            usuarioUi.setId(usuario.getUsuarioId());
            usuarioUi.setNombre(usuario.getUsuario());
            usuarioUi.setClave(usuario.getPassword());
            usuarioUi.setPersonUi(personUi);

            usuarioUiList.add(usuarioUi);

        }

        callback.onLoad(true, usuarioUiList);

    }

    @Override
    public void eliminarUsuario(UsuarioUi usuarioUi, Callback<UsuarioUi> callback) {



        PersonUi personUi = usuarioUi.getPersonUi();

        SQLite.delete(Persona.class)
                .where(Persona_Table.personaId.eq(personUi.getId()))
                .async()
                .execute();
        SQLite.delete(Usuario.class)
                .where(Usuario_Table.usuarioId.eq(usuarioUi.getId()))
                .async()
                .execute();
        callback.onLoad(true, usuarioUi);

    }

    @Override
    public void editarUsuario(UsuarioUi usuarioUi, Callback<UsuarioUi> callback) {

        Log.d("UsuarioLocalDataSOurce","editarUsuario: "+usuarioUi.getId());
        try {
            PersonUi personUi = usuarioUi.getPersonUi();

            Persona persona = SQLite.select().from(Persona.class)
                    .where(Persona_Table.personaId.eq(personUi.getId()))
                    .querySingle();


            //persona.setPersonaId(persona.getPersonaId());
            persona.setNombres(personUi.getNombre());
            persona.setApellidos(personUi.getApellido());
            persona.setCelular(personUi.getTelefono());

            if (!persona.save())throw new Error("Error al editar Persona");

            Usuario usuario = SQLite.select().from(Usuario.class)
                    .where(Usuario_Table.usuarioId.eq(personUi.getId()))
                    .querySingle();

            //usuario.setUsuarioId(usuarioUi.getId());
            usuario.setUsuario(usuarioUi.getNombre());
            usuario.setPassword(usuarioUi.getClave());

            //usuario.setPersonaId(persona.getPersonaId());
            if (!usuario.save())throw  new Error("Error al editar Usuario");

            callback.onLoad(true,usuarioUi);

        }catch (Exception e){
            e.printStackTrace();
            callback.onLoad(false, null);
        }


    }

    @Override
    public void getUsuario(UsuarioUi usuarioUi, Callback<UsuarioUi> callback) {


        PersonUi personUi = usuarioUi.getPersonUi();

        Persona persona = SQLite.select().from(Persona.class)
                .where(Persona_Table.personaId.eq(personUi.getId()))
                .querySingle();


        personUi.setId(persona.getPersonaId());
        personUi.setNombre(persona.getNombres());
        personUi.setApellido(persona.getApellidos());
        personUi.setTelefono(persona.getCelular());

        Usuario usuario = SQLite.select().from(Usuario.class)
                .where(Usuario_Table.usuarioId.eq(usuarioUi.getId()))
                .querySingle();

        usuarioUi.setId(usuario.getUsuarioId());
        usuarioUi.setNombre(usuario.getUsuario());
        usuarioUi.setClave(usuario.getPassword());

        usuarioUi.setPersonUi(personUi);


        callback.onLoad(true, usuarioUi);
    }
}
