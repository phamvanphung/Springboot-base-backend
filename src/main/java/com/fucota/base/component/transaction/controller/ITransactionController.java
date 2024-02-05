package com.fucota.base.component.transaction.controller;

import com.fucota.base.component.transaction.dto.request.GetPageTransactionRequest;
import com.fucota.base.component.transaction.dto.response.TransactionDetailResponse;
import com.fucota.base.component.transaction.dto.response.TransactionInfoResponse;
import com.fucota.base.core.ResponseBase;
import com.fucota.base.core.ResponseData;
import com.fucota.base.core.dto.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Transaction Controller")
@RequestMapping("/transaction/v1")
public interface ITransactionController {

    @Operation(
        summary = "Get Transaction"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBase<TransactionDetailResponse>> getDetail (@PathVariable(name = "id") String id);


    @Operation(
        summary = "Get Page Transaction"
    )
    @GetMapping("/device/{did}")
    public ResponseEntity<ResponseBase<PageResponse<TransactionInfoResponse>>> getPage(@PathVariable(required = false, name = "did") String deviceId, @ParameterObject GetPageTransactionRequest request);
}
