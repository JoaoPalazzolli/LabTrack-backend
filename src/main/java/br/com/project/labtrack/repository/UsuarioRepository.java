package br.com.project.labtrack.repository;

import br.com.project.labtrack.domain.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmail(String email);

    @Modifying
    @Query("""
            UPDATE Usuario u SET u.nome = :nome
            WHERE u.id = :id
            """)
    void updateNomeByUsuarioId(UUID id, String nome);
}
