package com.fucota.base.component.payment.controller;

import com.fucota.base.component.payment.dto.request.CreateQrRequest;
import com.fucota.base.component.payment.dto.response.CheckAccountResponse;
import com.fucota.base.component.payment.dto.response.CreateQrResponse;
import com.fucota.base.component.transaction.dto.response.TransactionInfoResponse;
import com.fucota.base.core.ResponseBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Payment Controller")
@RequestMapping("/payment/v1")
public interface IPaymentController {

    @Operation(
        summary = "Check Account"
    )
    @GetMapping("/check_account/{account}")
    public ResponseEntity<ResponseBase<CheckAccountResponse>> checkAccount(@PathVariable(name = "account") String account);



    @Operation(
        summary = "Create QR code"
    )
    @PostMapping("/paymentQr")
    public ResponseEntity<ResponseBase<CreateQrResponse>> createPaymentQr(@Validated @RequestBody CreateQrRequest request);


    @Operation(
        summary = "Cancel Transaction"
    )
    @PutMapping("/cancel/{id}")
    public ResponseEntity<ResponseBase<TransactionInfoResponse>> cancle(@PathVariable(name = "id") String id );
}
