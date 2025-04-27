package br.com.project.labtrack.repository;

import br.com.project.labtrack.domain.MonitoramentoTransporte;
import br.com.project.labtrack.domain.Usuario;
import br.com.project.labtrack.infra.utils.StatusTransporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MonitoramentoTransporteRepository extends JpaRepository<MonitoramentoTransporte, UUID> {

    @Query("""
            SELECT m FROM MonitoramentoTransporte
            INNER JOIN InventarioItem i ON m.item.codigoItem = i.codigoItem
            WHERE i.usuario.id = :usuarioId
            """)
    List<MonitoramentoTransporte> findAllTransporteByUsuarioId(UUID usuarioId);

    @Query("""
            UPDATE FROM MonitoramentoTransporte m SET m.statusTransporte = :status
            WHERE m.codigoTransporte = :transporteId
            """)
    void updateStatusTransporte(UUID transporteId, StatusTransporte status);

    @Query("""
            UPDATE FROM MonitoramentoTransporte m SET m.usuarioRecebido = :usuario
            WHERE m.codigoTransporte = :transporteId
            """)
    void updateUsuarioRecebido(UUID transporteId, Usuario usuario);
}
