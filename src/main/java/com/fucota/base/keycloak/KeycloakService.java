package com.fucota.base.keycloak;

import com.fucota.base.keycloak.config.KeycloakProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
@Slf4j
public class KeycloakService {

    private static Keycloak keycloakAdmin = null;
    private final KeycloakProperties keycloakProperties;


    @Bean
    private Keycloak initAdmin() {
        keycloakAdmin = KeycloakBuilder.builder()
            .serverUrl(keycloakProperties.getServerUrl())
            .realm(keycloakProperties.getRealm())
            .grantType(OAuth2Constants.PASSWORD)
            .clientId(keycloakProperties.getClientId())
            .clientSecret(keycloakProperties.getClientId())
            .username(keycloakProperties.getUsernameAdmin())
            .password(keycloakProperties.getPasswordAdmin())
            .build();
        return keycloakAdmin;
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


    public AccessTokenResponse getAccessTokenFromRefresh(String refreshToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = keycloakProperties.getServerUrl() + "/realms/" + keycloakProperties.getRealm() + "/protocol/openid-connect/token";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", refreshToken);
        map.add("client_id", keycloakProperties.getClientId());
        map.add("client_secret", keycloakProperties.getSecret());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<AccessTokenResponse> response = restTemplate.postForEntity(url, request, AccessTokenResponse.class);
        return response.getBody();
    }
}
