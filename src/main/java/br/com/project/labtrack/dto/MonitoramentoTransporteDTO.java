package br.com.project.labtrack.dto;

import br.com.project.labtrack.infra.utils.StatusTransporte;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MonitoramentoTransporteDTO {

    private UUID codigoTransporte;
    private UsuarioDTO usuarioEnviado;
    private UsuarioDTO usuarioRecebido;
    private LocalDateTime dataRecebimento;
    private LocalDateTime dataSaida;
    private StatusTransporte statusTransporte;
}
