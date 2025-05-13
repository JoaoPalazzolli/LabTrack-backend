package br.com.project.labtrack.repository;

import br.com.project.labtrack.domain.Permissao;
import br.com.project.labtrack.infra.utils.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PermissaoRepository extends JpaRepository<Permissao, UUID> {

    @Query("""
            SELECT p FROM Permissao p WHERE p.descricao = :descricao
            """)
    Optional<Permissao> findByDescricao(UserRole descricao);
}
