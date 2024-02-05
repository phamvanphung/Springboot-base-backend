package com.fucota.base.component.paygate.controller;

import com.fucota.base.component.paygate.dto.request.EncryptedBodyRequest;
import com.fucota.base.core.ResponseBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.unicloud.sdk.payment.security.HeaderBase;

@Tag(name = "Paygate Callback Controller", description = "Handle callback từ paygate")
@RequestMapping(value = "/api/paygate/callback")
public interface IPaygateController {

    @Operation(
        summary = "Kiểm tra tài khoản ảo",
        description = "- Kiểm tra tài khoản ảo"
    )
    @PostMapping("/v2/inquiryChecking")
    ResponseEntity<ResponseBase<String>> inquiryChecking(
        @RequestHeader(HeaderBase.CLIENT) String clientId,
        @RequestHeader(HeaderBase.SIGNATURE) String signature,
        @RequestHeader(HeaderBase.TIMESTAMP) Long timestamp,
        @Valid @RequestBody EncryptedBodyRequest request
    );

    @Operation(
        summary = "Kiểm tra trước khi gọi hoạch toán",
        description = "- Kiểm tra trước khi gọi hoạch toán"
    )
    @PostMapping("/v2/depositChecking")
    ResponseEntity<ResponseBase<String>> depositChecking(
        @RequestHeader(HeaderBase.CLIENT) String clientId,
        @RequestHeader(HeaderBase.SIGNATURE) String signature,
        @RequestHeader(HeaderBase.TIMESTAMP) Long timestamp,
        @Valid @RequestBody EncryptedBodyRequest request
    );

    @Operation(
        summary = "Cập nhật trạng thái giao dịch",
        description = "- Cập nhật trạng thái giao dịch"
    )
    @PostMapping("/v2/notifyTransaction")
    ResponseEntity<ResponseBase<String>> notifyTransaction(
        @RequestHeader(HeaderBase.CLIENT) String clientId,
        @RequestHeader(HeaderBase.SIGNATURE) String signature,
        @RequestHeader(HeaderBase.TIMESTAMP) Long timestamp,
        @Valid @RequestBody EncryptedBodyRequest request
    );
}
