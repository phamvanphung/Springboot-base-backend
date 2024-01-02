package com.fucota.base.keycloak.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.AuthorizationContext;
import org.keycloak.adapters.authorization.PolicyEnforcer;
import org.keycloak.adapters.authorization.TokenPrincipal;
import org.keycloak.adapters.authorization.integration.elytron.ServletHttpRequest;
import org.keycloak.adapters.authorization.integration.elytron.ServletHttpResponse;
import org.keycloak.adapters.authorization.spi.ConfigurationResolver;
import org.keycloak.adapters.authorization.spi.HttpRequest;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class CustomFilter extends OncePerRequestFilter {

    private final Map<PolicyEnforcerConfig, PolicyEnforcer> policyEnforcer;
    private final ConfigurationResolver configResolver;

    public CustomFilter(ConfigurationResolver configResolver) {
        this.configResolver = configResolver;
        this.policyEnforcer = Collections.synchronizedMap(new HashMap());
    }


    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        if (requestHasToken(servletRequest)) {
            ServletHttpRequest httpRequest = new ServletHttpRequest(servletRequest, new TokenPrincipal() {
                public String getRawToken() {
                    return extractBearerToken(servletRequest);
                }
            });
            PolicyEnforcer policyEnforcer = this.getOrCreatePolicyEnforcer(servletRequest, httpRequest);
            AuthorizationContext authzContext = policyEnforcer.enforce(httpRequest, new ServletHttpResponse(servletResponse));
            servletRequest.setAttribute(AuthorizationContext.class.getName(), authzContext);
            if (authzContext.isGranted()) {
                log.debug("Request authorized, continuing the filter chain");
                filterChain.doFilter(request, response);
            } else {
                log.error("Unauthorized servletRequest to path [%s], aborting the filter chain", servletRequest.getRequestURI());
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean requestHasToken(HttpServletRequest request) {
        // Implement your logic to check if the request has a token
        // For example, check the Authorization header for a Bearer token
        String authorizationHeader = request.getHeader("Authorization");
        return authorizationHeader != null && authorizationHeader.startsWith("Bearer ");
    }

    protected String extractBearerToken(HttpServletRequest request) {
        Enumeration<String> authorizationHeaderValues = request.getHeaders("Authorization");

        while (authorizationHeaderValues.hasMoreElements()) {
            String value = (String) authorizationHeaderValues.nextElement();
            String[] parts = value.trim().split("\\s+");
            if (parts.length == 2) {
                String bearer = parts[0];
                if (bearer.equalsIgnoreCase("Bearer")) {
                    return parts[1];
                }
            }
        }

        return null;
    }

    private PolicyEnforcer getOrCreatePolicyEnforcer(final HttpServletRequest servletRequest, HttpRequest request) {
        return (PolicyEnforcer) this.policyEnforcer.computeIfAbsent(this.configResolver.resolve(request), new Function<PolicyEnforcerConfig, PolicyEnforcer>() {
            public PolicyEnforcer apply(PolicyEnforcerConfig enforcerConfig) {
                return createPolicyEnforcer(servletRequest, enforcerConfig);
            }
        });
    }

    protected PolicyEnforcer createPolicyEnforcer(HttpServletRequest servletRequest, PolicyEnforcerConfig enforcerConfig) {
        String authServerUrl = enforcerConfig.getAuthServerUrl();
        return PolicyEnforcer.builder().authServerUrl(authServerUrl).realm(enforcerConfig.getRealm()).clientId(enforcerConfig.getResource()).credentials(enforcerConfig.getCredentials()).bearerOnly(false).enforcerConfig(enforcerConfig).build();
    }


}
