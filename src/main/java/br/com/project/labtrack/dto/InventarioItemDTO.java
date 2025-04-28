package br.com.project.labtrack.dto;

import br.com.project.labtrack.infra.utils.ClassificacaoRisco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InventarioItemDTO {

    private UUID codigoItem;
    private String descricao;
    private String fornecedor;
    private String condicoesArmazenamento;
    private ClassificacaoRisco classificacaoRisco;
    private LocalDate dataFabricacao;
    private LocalDate dataVencimento;
    private LocalDateTime data_registro;
    private LocalDateTime ultimaAlteracaoData;

}
