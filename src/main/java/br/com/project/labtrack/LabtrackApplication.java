package br.com.project.labtrack;

import br.com.project.labtrack.domain.Permissao;
import br.com.project.labtrack.infra.utils.UserRole;
import br.com.project.labtrack.repository.PermissaoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LabtrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabtrackApplication.class, args);
	}

}
