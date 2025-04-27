package br.com.project.labtrack.service.impl.auth;

import br.com.project.labtrack.domain.Usuario;
import br.com.project.labtrack.dto.TokenDTO;
import br.com.project.labtrack.dto.auth.UserAuthDTO;
import br.com.project.labtrack.infra.security.jwt.service.JwtService;
import br.com.project.labtrack.infra.utils.UsuarioAutenticado;
import br.com.project.labtrack.repository.PermissaoRepository;
import br.com.project.labtrack.repository.UsuarioRepository;
import br.com.project.labtrack.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Override
    public ResponseEntity<TokenDTO> autenticarUsuario(UserAuthDTO userAuthDTO) {
        try {
            final String email = userAuthDTO.getEmail();
            final String senha = userAuthDTO.getSenha();

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, senha));

            var user = usuarioRepository.findByEmail(email).orElseThrow();

            var tokenDTO = jwtService.criarToken(user);

            return ResponseEntity.ok(tokenDTO);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Email ou senha inválido!");
        }
    }

    @Override
    public ResponseEntity<TokenDTO> registrarUsuario(UserAuthDTO userAuthDTO) {
        var permissao = permissaoRepository.findByDescricao(userAuthDTO.getRole())
                .orElseThrow(() -> new RuntimeException("Permissão não encontrada")); // arrumar Exception

        var usuario = Usuario.builder()
                .email(userAuthDTO.getEmail())
                .senha(passwordEncoder.encode(userAuthDTO.getSenha()))
                .nome(userAuthDTO.getNome())
                .permissoes(Collections.singletonList(permissao))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();

        usuarioRepository.save(usuario);

        var tokenDTO = jwtService.criarToken(usuario);

        return ResponseEntity.ok(tokenDTO);
    }

    @Override
    public ResponseEntity<TokenDTO> atualizarToken(String refreshToken) {
        if(refreshToken.startsWith("Bearer ")){
            refreshToken = refreshToken.substring("Bearer ".length());
        }

        var user = UsuarioAutenticado.pegarUsuarioAutenticado();

        if(!jwtService.isTokenValid(refreshToken, user)){
            throw new RuntimeException("Token Inválido!");
        }

        var tokenDTO = jwtService.criarToken(user);

        return ResponseEntity.ok(tokenDTO);
    }
}
