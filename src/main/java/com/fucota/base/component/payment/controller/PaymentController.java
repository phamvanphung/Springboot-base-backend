package com.fucota.base.component.payment.controller;


import com.fucota.base.component.payment.dto.request.CancelPaymentRequest;
import com.fucota.base.component.payment.dto.request.CheckAccountRequest;
import com.fucota.base.component.payment.dto.request.CreateQrRequest;
import com.fucota.base.component.payment.dto.response.CheckAccountResponse;
import com.fucota.base.component.payment.dto.response.CreateQrResponse;
import com.fucota.base.component.transaction.dto.response.TransactionInfoResponse;
import com.fucota.base.core.BaseController;
import com.fucota.base.core.ResponseBase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController extends BaseController implements IPaymentController {

    @Override
    public ResponseEntity<ResponseBase<CheckAccountResponse>> checkAccount(String account) {
        return this.execute(new CheckAccountRequest(account));
    }


    @Override
    public ResponseEntity<ResponseBase<CreateQrResponse>> createPaymentQr(CreateQrRequest request) {
        return this.execute(request);
    }


    @Override
    public ResponseEntity<ResponseBase<TransactionInfoResponse>> cancle(String id) {
        return this.execute(new CancelPaymentRequest(id));
    }
}
