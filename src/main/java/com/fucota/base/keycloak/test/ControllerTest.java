package com.fucota.base.keycloak.test;

import com.fucota.base.keycloak.KeycloakService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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


    @PostMapping("/refresh")
    ResponseEntity<AccessTokenResponse> refresh(@RequestParam String refresh) {
        AccessTokenResponse response = keycloakService.getAccessTokenFromRefresh(refresh);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/user")
    ResponseEntity<String> refresh(@AuthenticationPrincipal Jwt jwt) {
        return new ResponseEntity<>(String.format("Hello, %s!", jwt.getClaimAsString("preferred_username")), HttpStatus.OK);
    }
}
