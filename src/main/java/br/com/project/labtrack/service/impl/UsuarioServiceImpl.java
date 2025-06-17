package br.com.project.labtrack.service.impl;

import br.com.project.labtrack.dto.UsuarioDTO;
import br.com.project.labtrack.infra.exceptions.ObjectNotFound;
import br.com.project.labtrack.utils.Mapper;
import br.com.project.labtrack.infra.utils.UsuarioAutenticado;
import br.com.project.labtrack.repository.UsuarioRepository;
import br.com.project.labtrack.service.UsuarioService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ResponseEntity<UsuarioDTO>  buscarPorId(UUID usuarioId) {
        var usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ObjectNotFound("Usuário não encontrado"));

        var dto = Mapper.parseTo(usuario, UsuarioDTO.class);

        return ResponseEntity.ok(dto);
    }


    @Override
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        var usuarios = usuarioRepository.findAll();

        return ResponseEntity.ok(Mapper.parseListTo(usuarios, UsuarioDTO.class));
    }

    @Transactional
    @Override
    public ResponseEntity<Void> atualizarNomeUsuario(String nome) {
        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        usuarioRepository.updateNomeByUsuarioId(user.getId(), nome);

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @Override
    public ResponseEntity<Void> deletarUsuario() {
        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        usuarioRepository.delete(user);

        return ResponseEntity.noContent().build();
    }
}
