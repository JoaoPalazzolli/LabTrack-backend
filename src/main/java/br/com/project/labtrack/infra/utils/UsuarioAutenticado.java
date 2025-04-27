package br.com.project.labtrack.infra.utils;

import br.com.project.labtrack.domain.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;

public class UsuarioAutenticado {

    public static Usuario pegarUsuarioAutenticado(){
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
