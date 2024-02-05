package com.fucota.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.unicloud.sdk.payment.KPayPacker;

@Configuration
public class MiddlewareClientConfig {

    @Value("${uni-pos.middleware.client-id}")
    private String middlewareClientId;

    @Value("${uni-pos.middleware.encrypt-key}")
    private String middlewareEncryptKey;

    @Value("${uni-pos.middleware.secret-key}")
    private String middlewareSecretKey;

    @Value("${uni-pos.middleware.accept-time-diff}")
    private Long acceptDiffTime;

    @Bean
    public KPayPacker middlewarePacker() {
        return new KPayPacker(middlewareClientId, middlewareEncryptKey, middlewareSecretKey, acceptDiffTime);
    }

}
