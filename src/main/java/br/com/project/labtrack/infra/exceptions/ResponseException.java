package br.com.project.labtrack.infra.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseException {

    private String mensagem;
    private LocalDateTime timestamp;
    private String detalhes;

}
