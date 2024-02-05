package com.fucota.base.component.transaction.controller;

import com.fucota.base.component.transaction.dto.request.GetDetailTransactionRequest;
import com.fucota.base.component.transaction.dto.request.GetPageTransactionRequest;
import com.fucota.base.component.transaction.dto.response.TransactionDetailResponse;
import com.fucota.base.component.transaction.dto.response.TransactionInfoResponse;
import com.fucota.base.core.BaseController;
import com.fucota.base.core.ResponseBase;
import com.fucota.base.core.dto.PageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController extends BaseController implements ITransactionController {

    @Override
    public ResponseEntity<ResponseBase<TransactionDetailResponse>> getDetail(String id) {
        return this.execute(new GetDetailTransactionRequest(id));
    }


    @Override
    public ResponseEntity<ResponseBase<PageResponse<TransactionInfoResponse>>> getPage(String deviceId, GetPageTransactionRequest request) {
        return this.execute(request.setDeviceId(deviceId));
    }
}
