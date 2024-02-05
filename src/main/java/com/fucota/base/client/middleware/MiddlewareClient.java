package com.fucota.base.client.middleware;

import com.fucota.base.client.CoreResponseBase;
import com.fucota.base.client.RestClient;
import com.fucota.base.client.middleware.request.CheckBeneficiaryCoreRequest;
import com.fucota.base.client.middleware.response.CheckBeneficiaryCoreResponse;
import com.fucota.base.core.enums.CommonResponseCode;
import com.fucota.base.core.exception.BusinessException;
import com.fucota.base.utils.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.unicloud.sdk.payment.KPayPacker;
import vn.unicloud.sdk.payment.client.EncryptedBodyRequest;
import vn.unicloud.sdk.payment.security.HeaderBase;
import vn.unicloud.sdk.payment.security.PackedMessage;

import java.util.Objects;

@Component
@Log4j2
@RequiredArgsConstructor
public class MiddlewareClient {

    private static final String CHECK_BENEFICIARY_V2 = "/api/umee/v2/checkBeneficiary";

    private final RestClient restClient;
    private final KPayPacker middlewarePacker;

    @Value("${uni-pos.middleware.host}")
    private String middlewareHost;
    @Value("${uni-pos.middleware.key}")
    private String middlewareKey;

    private void validateHeader(HttpHeaders httpHeaders) {
        try {
            if (httpHeaders.containsKey(HeaderBase.CLIENT) &&
                httpHeaders.containsKey(HeaderBase.SIGNATURE) &&
                httpHeaders.containsKey(HeaderBase.TIMESTAMP)) {
                return;
            }
        } catch (Exception e) {
            log.error("Check header error: {}", e.getMessage());
        }
        throw new BusinessException(CommonResponseCode.THIRD_PARTY_API_ERROR);
    }

    @SneakyThrows
    private <T> T execute(String host, String path, HttpMethod method, Object request, Class<T> responseClassType) {
        PackedMessage packedMessage = middlewarePacker.encode(request);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HeaderBase.CLIENT, packedMessage.getClientId());
        headers.add(HeaderBase.SIGNATURE, packedMessage.getSignature());
        headers.add(HeaderBase.TIMESTAMP, String.valueOf(packedMessage.getTimestamp()));
        EncryptedBodyRequest encryptedBodyRequest = new EncryptedBodyRequest(packedMessage.getEncryptedData());
        ResponseEntity<CoreResponseBase> response = restClient.callAPI(
            String.format("%s%s", host, path),
            method,
            headers,
            encryptedBodyRequest,
            CoreResponseBase.class);
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new BusinessException(CommonResponseCode.THIRD_PARTY_API_ERROR);
        }
        log.debug("Response: {}", response.getBody());
        if (response.getBody() != null && StringUtils.isNotBlank(response.getBody().getMessage())) {
            int errCode = Integer.parseInt(response.getBody().getCode()) + 100000;
            throw new BusinessException(CommonResponseCode.fromCode(errCode));
        }
        this.validateHeader(response.getHeaders());

        CoreResponseBase<?> baseResponse = response.getBody();
        if (baseResponse == null) {
            throw new BusinessException(CommonResponseCode.THIRD_PARTY_API_ERROR);
        }
        if (!baseResponse.isSuccess()) {
            throw new BusinessException(CommonResponseCode.THIRD_PARTY_API_ERROR);
        }
        String encryptedData = ModelMapperUtils.mapper(baseResponse.getData(), String.class);
        return middlewarePacker.decode(
            PackedMessage.builder()
                .clientId(response.getHeaders().getFirst(HeaderBase.CLIENT))
                .timestamp(Long.parseLong(Objects.requireNonNull(response.getHeaders().getFirst(HeaderBase.TIMESTAMP))))
                .signature(response.getHeaders().getFirst(HeaderBase.SIGNATURE))
                .encryptedData(encryptedData)
                .build(),
            responseClassType
        );
    }

    public CheckBeneficiaryCoreResponse checkBeneficiary(String bin, String accountNo) {
        return this.execute(
            middlewareHost,
            CHECK_BENEFICIARY_V2,
            HttpMethod.POST,
            new CheckBeneficiaryCoreRequest(bin, accountNo),
            CheckBeneficiaryCoreResponse.class
        );
    }
}
