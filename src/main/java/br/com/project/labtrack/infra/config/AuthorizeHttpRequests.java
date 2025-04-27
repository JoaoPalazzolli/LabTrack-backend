package br.com.project.labtrack.infra.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "authorize.http.requests")
@Data
public class AuthorizeHttpRequests {

    private String permitAll;
    private String authenticated;
    private String denyAll;

}
