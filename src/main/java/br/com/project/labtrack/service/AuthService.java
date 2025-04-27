package br.com.project.labtrack.service;

import br.com.project.labtrack.domain.Usuario;
import br.com.project.labtrack.dto.TokenDTO;
import br.com.project.labtrack.dto.auth.UserAuthDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<TokenDTO> autenticarUsuario(UserAuthDTO userAuthDTO);
    ResponseEntity<TokenDTO> atualizarToken(String refreshToken);
    ResponseEntity<TokenDTO> registrarUsuario(UserAuthDTO userAuthDTO);
    Usuario pegarUsuarioAutenticado();

}
