package br.com.project.labtrack.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping(value = "/nome/{nome}")
    public ResponseEntity<Void> atualizarNomeUsuario(@PathVariable(value = "nome") String nome){
        return usuarioService.atualizarNomeUsuario(nome);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarUsuario(){
        return usuarioService.deletarUsuario();
    }

}
