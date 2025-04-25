package br.com.project.labtrack.dto;


import br.com.project.labtrack.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class UsuarioDTO {

    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private String role;

}
