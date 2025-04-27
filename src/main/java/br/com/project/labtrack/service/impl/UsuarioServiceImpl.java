package br.com.project.labtrack.service.impl;

import br.com.project.labtrack.domain.Usuario;
import br.com.project.labtrack.dto.UsuarioDTO;
import br.com.project.labtrack.infra.utils.Mapper;
import br.com.project.labtrack.repository.UsuarioRepository;
import br.com.project.labtrack.service.UsuarioService;

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
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")); // arrumar exceptions

        var dto = Mapper.parseTo(usuario, UsuarioDTO.class);

        return ResponseEntity.ok(dto);
    }


    @Override
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        var usuarios = usuarioRepository.findAll();

        return ResponseEntity.ok(Mapper.parseListTo(usuarios, UsuarioDTO.class));
    }

    @Override
    public ResponseEntity<Void> atualizarUsuario(UUID usuarioId, UsuarioDTO usuarioDTO) {
        // verificar se existe esse registro no banco
        var usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));// arrumar exception

        usuario = Mapper.parseTo(usuarioDTO, Usuario.class);
        usuario.setId(usuarioId);

        usuarioRepository.save(usuario);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deletarUsuario(UUID usuarioId) {
        var usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);

        return ResponseEntity.noContent().build();
    }
}
