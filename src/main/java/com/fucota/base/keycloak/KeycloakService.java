package com.fucota.base.keycloak;

import com.fucota.base.keycloak.config.KeycloakProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakService {

    private static Keycloak keycloakAdmin = null;
    private final KeycloakProperties keycloakProperties;

    @Bean
    private void initAdmin() {
        keycloakAdmin = KeycloakBuilder.builder()
            .serverUrl(keycloakProperties.getServerUrl())
            .realm(keycloakProperties.getRealm())
            .grantType(OAuth2Constants.PASSWORD)
            .clientId(keycloakProperties.getClientId())
            .clientSecret(keycloakProperties.getClientId())
            .username(keycloakProperties.getUsernameAdmin())
            .password(keycloakProperties.getPasswordAdmin())
            .build();
    }


    public AccessTokenResponse getJWT(String username, String password) {
        Keycloak keycloak = Keycloak.getInstance(
            keycloakProperties.getServerUrl(),
            keycloakProperties.getRealm(),
            username,
            password,
            keycloakProperties.getClientId(),
            keycloakProperties.getSecret()
        );
        return keycloak.tokenManager().getAccessToken();
    }
}
