package com.fucota.base.client;

import com.fucota.base.core.enums.CommonResponseCode;
import com.fucota.base.core.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class RestClient implements IRestClient {

    private final RestTemplate restTemplate;

    public RestClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public <T> ResponseEntity<T> callAPI(String url, HttpMethod httpMethod, HttpHeaders httpHeaders, Object request, Class<T> responseClassType) {
        log.info("Start using rest client connect to the outside server");
        try {
            HttpEntity<Object> httpEntity = new HttpEntity<>(request, httpHeaders);

            log.info("Calling EXTERNAL {} {}", httpMethod, url);

            return restTemplate.exchange(url, httpMethod, httpEntity, responseClassType);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new BusinessException(CommonResponseCode.THIRD_PARTY_API_ERROR);
        }
    }
}
