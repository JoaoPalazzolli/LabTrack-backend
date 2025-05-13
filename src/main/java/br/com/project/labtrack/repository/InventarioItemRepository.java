package br.com.project.labtrack.repository;

import br.com.project.labtrack.domain.InventarioItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventarioItemRepository extends JpaRepository<InventarioItem, UUID> {

    @Query("""
            SELECT i FROM InventarioItem i WHERE i.codigoItem = :codigoItem AND i.usuario.id = :usuarioId
            """)
    Optional<InventarioItem> findByCodigoItemAndUsuarioId(UUID codigoItem, UUID usuarioId);

    @Query("""
            SELECT i FROM InventarioItem i WHERE i.usuario.id = :usuarioId
            """)
    List<InventarioItem> findAllByUsuarioId(UUID usuarioId);

    @Query("""
            SELECT i FROM InventarioItem i WHERE LOWER(i.descricao) LIKE LOWER(CONCAT('%', :descricao, '%')) AND i.usuario.id = :usuarioId
            """)
    List<InventarioItem> findByDescricao(UUID usuarioId, String descricao);
}
