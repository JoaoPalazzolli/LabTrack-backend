package br.com.project.labtrack.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_monitoramento_transporte")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MonitoramentoTransporte {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID codigoTransporte;

    // implementar usuario recebido e usuario enviado

    @Column(name = "data_recebimento")
    private LocalDateTime dataRecebimento;

    @Column(name = "data_saida")
    private LocalDateTime dataSaida;

    @ManyToOne
    @JoinColumn(name = "codigo_item")
    private InventarioItem item;
}
