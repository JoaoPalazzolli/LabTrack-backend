package br.com.project.labtrack.controller;

import br.com.project.labtrack.dto.TokenDTO;
import br.com.project.labtrack.dto.auth.UserAuthDTO;
import br.com.project.labtrack.repository.PermissaoRepository;
import br.com.project.labtrack.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/teste")
    private void carregarPermissao(){

    }

    @PostMapping(value = "/autenticar")
    public ResponseEntity<TokenDTO> autenticarUsuario(@RequestBody UserAuthDTO userAuthDTO){
        return authService.autenticarUsuario(userAuthDTO);
    }

    @PostMapping(value = "/registrar")
    public ResponseEntity<TokenDTO> registrarUsuario(@RequestBody UserAuthDTO userAuthDTO){
        return authService.registrarUsuario(userAuthDTO);
    }

    @PutMapping(value = "/refresh")
    public ResponseEntity<TokenDTO> atualizarToken(@RequestHeader("Authorization") String refreshToken){
        return authService.atualizarToken(refreshToken);
    }

}
