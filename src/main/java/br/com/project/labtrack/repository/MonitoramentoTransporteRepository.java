package br.com.project.labtrack.repository;

import br.com.project.labtrack.domain.MonitoramentoTransporte;
import br.com.project.labtrack.domain.Usuario;
import br.com.project.labtrack.utils.StatusTransporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MonitoramentoTransporteRepository extends JpaRepository<MonitoramentoTransporte, UUID> {

    @Query("""
            SELECT m FROM MonitoramentoTransporte m
            JOIN m.itens i
            WHERE i.usuario.id = :usuarioId
            """)
    List<MonitoramentoTransporte> findAllTransporteByUsuarioId(UUID usuarioId);

    @Modifying
    @Query("""
            UPDATE MonitoramentoTransporte m SET m.statusTransporte = :status
            WHERE m.codigoTransporte = :codigoTransporte
            """)
    void updateStatusTransporte(UUID codigoTransporte, StatusTransporte status);

    @Modifying
    @Query("""
            UPDATE MonitoramentoTransporte m SET m.usuarioRecebido = :usuario, m.dataRecebimento = CURRENT_TIMESTAMP
            WHERE m.codigoTransporte = :codigoTransporte
            """)
    void updateUsuarioRecebido(UUID codigoTransporte, Usuario usuario);

    @Modifying
    @Query("""
            UPDATE MonitoramentoTransporte m
            SET m.qrCodeImageUrl = :qrCodeImageUrl
            WHERE m.codigoTransporte = :codigoTransporte
            """)
    void updateQrCodeImageUrlTransporte(UUID codigoTransporte, String qrCodeImageUrl);
}
