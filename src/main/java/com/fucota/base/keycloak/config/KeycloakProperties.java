package com.fucota.base.keycloak.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "keycloak")
@PropertySource("classpath:application.yml")
@Data
public class KeycloakProperties {
    private String realm;
    private String serverUrl;
    private String clientId;
    private String secret;
    private String passwordAdmin;
    private String usernameAdmin;

}
