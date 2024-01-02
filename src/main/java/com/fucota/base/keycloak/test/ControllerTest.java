package com.fucota.base.keycloak.test;

import com.fucota.base.keycloak.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ControllerTest {

    private final KeycloakService keycloakService;

    @PostMapping("/login")
    ResponseEntity<AccessTokenResponse> login(@RequestParam String username, @RequestParam String password) {
        AccessTokenResponse response = keycloakService.getJWT(username, password);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
