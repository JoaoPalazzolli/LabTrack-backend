package br.com.project.labtrack.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.labtrack.service.UsuarioService;
import br.com.project.labtrack.dto.UsuarioDTO;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //buscando todos os usuarios
    @GetMapping 
    public List<UsuarioDTO> listarTodos() {
        return usuarioService.listarTodos();
    }

    //buscando por id
    @GetMapping("/{id}")
    public UsuarioDTO buscarPorId(@PathVariable UUID id) {
        return usuarioService.buscarPorId(id)
        .map(usuario -> {
            UsuarioDTO dto = new UsuarioDTO();
            BeanUtils.copyProperties(usuario, dto);
            return dto;
        }).orElse(null);
    }
    

    //criar novo usuario
    @PostMapping
    public UsuarioDTO salvar(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.criarUsuario(usuarioDTO);
    }

    @PutMapping("/{id}")
    //atualizando usuario
    public UsuarioDTO atualizar(@PathVariable UUID id, @RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.atualizarUsuario(id, usuarioDTO);
    }

    //atualizando usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
