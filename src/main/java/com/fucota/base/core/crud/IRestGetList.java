package com.fucota.base.core.crud;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.fucota.base.core.BaseResponseData;
import com.fucota.base.core.ResponseBase;
import com.fucota.base.core.crud.dto.GetListRequest;


public interface IRestGetList<T extends GetListRequest, I extends BaseResponseData> {

    @GetMapping("/getList")
    @Operation(summary = "Lấy danh sách đối tượng", description = "Lấy danh sách đối tượng")
    ResponseEntity<ResponseBase<I>> getList(@Valid @ParameterObject T request);

}
