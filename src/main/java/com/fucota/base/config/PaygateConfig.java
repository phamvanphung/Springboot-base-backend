package com.fucota.base.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.unicloud.sdk.payment.KPayPacker;

@Configuration
public class PaygateConfig {
    @Value("${uni-pos.paygate.client-id}")
    private String paygateClientId;

    @Value("${uni-pos.paygate.encrypt-key}")
    private String paygateEncryptKey;

    @Value("${uni-pos.paygate.signature-key}")
    private String paygateSecretKey;

    @Value("${uni-pos.paygate.accept-time-diff}")
    private Long acceptDiffTime;


    @Bean
    public KPayPacker paygatePacker() {
        return new KPayPacker(paygateClientId, paygateEncryptKey, paygateSecretKey, acceptDiffTime);
    }
}
