package br.com.project.labtrack.domain;

import br.com.project.labtrack.infra.utils.StatusTransporte;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_monitoramento_transporte")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MonitoramentoTransporte {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codigoTransporte;

    @Column(name = "qr_code_image_url", unique = true)
    private String qrCodeImageUrl;

    @ManyToOne
    @JoinColumn(name = "usuario_enviado")
    private Usuario usuarioEnviado;

    @ManyToOne
    @JoinColumn(name = "usuario_recebido")
    private Usuario usuarioRecebido;

    @Column(name = "data_recebimento")
    private LocalDateTime dataRecebimento;

    @Column(name = "data_saida")
    private LocalDateTime dataSaida;

    @Column(name = "status_transporte")
    @Enumerated(EnumType.STRING)
    private StatusTransporte statusTransporte;

    @ManyToMany
    @JoinTable(name = "tb_transporte_itens", joinColumns = @JoinColumn(name = "codigo_transporte"),
            inverseJoinColumns = @JoinColumn(name = "codigo_item"))
    private List<InventarioItem> itens;
}
