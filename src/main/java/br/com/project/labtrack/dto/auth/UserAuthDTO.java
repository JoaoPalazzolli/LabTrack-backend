package br.com.project.labtrack.dto.auth;

import br.com.project.labtrack.utils.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserAuthDTO {

    private String nome;
    private String email;
    private String senha;
    private UserRole role;

}
