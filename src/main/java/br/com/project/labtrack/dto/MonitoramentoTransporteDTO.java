package br.com.project.labtrack.dto;

import br.com.project.labtrack.utils.StatusTransporte;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MonitoramentoTransporteDTO {

    private UUID codigoTransporte;
    private String qrCodeImageUrl;
    private UsuarioDTO usuarioEnviado;
    private UsuarioDTO usuarioRecebido;
    private LocalDateTime dataRecebimento;
    private LocalDateTime dataSaida;
    private StatusTransporte statusTransporte;
    private List<InventarioItemDTO> itens;

}
