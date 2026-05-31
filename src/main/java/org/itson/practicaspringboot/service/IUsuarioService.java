package org.itson.practicaspringboot.service;

import org.itson.practicaspringboot.model.usuario;

public interface IUsuarioService {
    usuario login(String correo, String password) throws Exception;
    usuario registrar(usuario usuario)throws Exception;
}
