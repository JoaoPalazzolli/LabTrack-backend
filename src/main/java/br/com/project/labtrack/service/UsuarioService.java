package br.com.project.labtrack.service;

import br.com.project.labtrack.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UsuarioService {

    ResponseEntity<List<UsuarioDTO>> listarTodos();
    ResponseEntity<Void> atualizarUsuario(UUID id, UsuarioDTO dto);
    ResponseEntity<Void> deletarUsuario(UUID id);
    ResponseEntity<UsuarioDTO> buscarPorId(UUID id);
    
}
