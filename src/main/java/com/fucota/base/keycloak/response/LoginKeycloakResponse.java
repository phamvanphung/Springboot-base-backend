package com.fucota.base.keycloak.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginKeycloakResponse {
    private String token;
    private String tokenExpireTime;
    private String refreshToken;
    private String refreshTokenExpireTime;
}
