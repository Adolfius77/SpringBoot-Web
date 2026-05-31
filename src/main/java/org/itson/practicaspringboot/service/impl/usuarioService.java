package org.itson.practicaspringboot.service.impl;

import jakarta.transaction.Transactional;
import org.itson.practicaspringboot.model.usuario;
import org.itson.practicaspringboot.repository.usuarioRepository;
import org.itson.practicaspringboot.service.IUsuarioService;
import org.springframework.stereotype.Service;

@Service
public class usuarioService implements IUsuarioService {
    private final usuarioRepository usuarioRepository;

    public usuarioService(usuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public usuario login(String correo, String password) throws Exception {
        if(correo == null || correo.isEmpty()){
            throw new Exception("El correo no es valido");
        }
        if(password == null || password.isEmpty()){
            throw new Exception("El password no es valido");
        }
        usuario usuario = usuarioRepository.findByCorreo(correo).orElseThrow(() -> new Exception("El usuario no existe"));

        if(!password.equals(usuario.getPassword())){
            throw new Exception("La contraseña es incorrecta");
        }
        return usuario;
    }

    @Override
    @Transactional
    public usuario registrar(usuario usuario) throws Exception {
        if(usuario.getCorreo() == null || usuario.getCorreo().isEmpty()){
            throw new Exception("El correo es obligatorio");
        }
        if(usuario.getPassword() == null || usuario.getPassword().isEmpty()){
            throw new Exception("la password es obligatoria");
        }
        if(usuario.getNombre() == null || usuario.getNombre().isEmpty()){
            throw new Exception("El nombre es obligatorio");
        }
        if(usuario.getApellido() == null || usuario.getApellido().isEmpty()){
            throw new Exception("El apellido es obligatorio");
        }
        String correo = usuario.getCorreo().toLowerCase();
        if(!correo.endsWith("@gmail.com") && !correo.endsWith("@outlook.com")){
            throw new Exception("el correo debe tener un dominio valido de correo como gmail.com o outlook.com");
        }

        usuario.setCorreo(correo);
        return usuarioRepository.save(usuario);
    }
}
