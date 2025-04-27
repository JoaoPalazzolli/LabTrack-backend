package br.com.project.labtrack.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.labtrack.service.UsuarioService;
import br.com.project.labtrack.dto.UsuarioDTO;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping 
    public ResponseEntity<List<UsuarioDTO>> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable(value = "userId") UUID userId) {
        return usuarioService.buscarPorId(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> atualizar(@PathVariable(value = "userId") UUID userId, @RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.atualizarUsuario(userId, usuarioDTO);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "userId") UUID userId){
        return usuarioService.deletarUsuario(userId);
    }

}
