package br.com.project.labtrack.service;

import br.com.project.labtrack.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UsuarioService {

    ResponseEntity<List<UsuarioDTO>> listarTodos();
    ResponseEntity<Void> atualizarNomeUsuario(String nome);
    ResponseEntity<Void> deletarUsuario();
    ResponseEntity<UsuarioDTO> buscarPorId(UUID id);
    
}
