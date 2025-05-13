package br.com.project.labtrack.dto;

import br.com.project.labtrack.infra.utils.ClassificacaoRisco;
import br.com.project.labtrack.infra.utils.TipoItem;
import br.com.project.labtrack.infra.utils.Unidade;
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

    private TipoItem tipoItem;
    private Double quantidade;
    private Unidade unidade;
    private String locLaboratorio;

    private String condicoesArmazenamento;
    private ClassificacaoRisco classificacaoRisco;
    private LocalDate dataFabricacao;
    private LocalDate dataVencimento;
    private LocalDateTime data_registro;
    private LocalDateTime ultimaAlteracaoData;



}
