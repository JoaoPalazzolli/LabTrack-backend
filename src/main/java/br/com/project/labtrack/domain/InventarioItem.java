package br.com.project.labtrack.domain;

import br.com.project.labtrack.infra.utils.ClassificacaoRisco;
import br.com.project.labtrack.infra.utils.TipoItem;
import br.com.project.labtrack.infra.utils.Unidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_inventario_item")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InventarioItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codigoItem;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "fornecedor")
    private String fornecedor;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_item", nullable = false)
    private TipoItem tipoItem;

    @Column
    private Double quantidade;

    @Column
    @Enumerated(EnumType.STRING)
    private Unidade unidade;

    @Column(name = "local_laboratorio")
    private String locLaboratorio;

    @Column(name = "condicoes_armazenamento", nullable = false)
    private String condicoesArmazenamento;

    @Column(name = "classificacao_risco", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ClassificacaoRisco classificacaoRisco;

    @Column(name = "data_fabricacao", nullable = false)
    private LocalDate dataFabricacao;
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "data_registro", nullable = false)
    @Builder.Default private final LocalDateTime data_registro = LocalDateTime.now();

    @Column(name = "ultima_alteracao_data")
    private LocalDateTime ultimaAlteracaoData;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
