package br.com.project.labtrack.domain;

import br.com.project.labtrack.utils.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Entity
@Table(name = "tb_permissao")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Permissao implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private UserRole descricao;

    @Override
    public String getAuthority() {
        return this.descricao.toString();
    }
}
