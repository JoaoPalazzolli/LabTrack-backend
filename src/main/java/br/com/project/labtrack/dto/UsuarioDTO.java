package br.com.project.labtrack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UsuarioDTO {

    private UUID id;
    private String nome;
    private String email;
    private LocalDateTime contaCriada;
}
