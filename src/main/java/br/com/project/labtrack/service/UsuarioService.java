package br.com.project.labtrack.service;

import br.com.project.labtrack.dto.UsuarioDTO;
import br.com.project.labtrack.domain.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {

    UsuarioDTO criarUsuario(UsuarioDTO dto);
    List<UsuarioDTO> listarTodos();
    UsuarioDTO atualizarUsuario(UUID id, UsuarioDTO dto);
    void deletarUsuario(UUID id);
    Optional<Usuario> buscarPorId(UUID id);
    
}
