package br.com.project.labtrack.service.impl;

import br.com.project.labtrack.domain.Usuario;
import br.com.project.labtrack.dto.UsuarioDTO;
import br.com.project.labtrack.repository.UsuarioRepository;
import br.com.project.labtrack.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO criarUsuario(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(dto, usuario);
        Usuario salvo = usuarioRepository.save(usuario);
        UsuarioDTO retorno = new UsuarioDTO();
        BeanUtils.copyProperties(salvo, retorno);
        return retorno;
    }

    @Override
    public Optional<Usuario> buscarPorId(UUID id) {
        return usuarioRepository.findById(id);
    }


    @Override
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> {
                    UsuarioDTO dto = new UsuarioDTO();
                    BeanUtils.copyProperties(usuario, dto);
                    return dto;
                }).collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO atualizarUsuario(UUID id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        BeanUtils.copyProperties(dto, usuario, "id");
        Usuario atualizado = usuarioRepository.save(usuario);
        UsuarioDTO retorno = new UsuarioDTO();
        BeanUtils.copyProperties(atualizado, retorno);
        return retorno;
    }

    @Override
    public void deletarUsuario(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioRepository.save(usuario);
    }
}
