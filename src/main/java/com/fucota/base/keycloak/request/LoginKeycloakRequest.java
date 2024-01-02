package com.fucota.base.keycloak.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginKeycloakRequest {
    private String username;
    private String password;
}
