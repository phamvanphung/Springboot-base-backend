package com.fucota.base.core.crud;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.fucota.base.core.BaseResponseData;
import com.fucota.base.core.ResponseBase;
import com.fucota.base.core.crud.dto.CreateRequest;


public interface IRestCreate<T extends CreateRequest, I extends BaseResponseData> {

    @PostMapping("/create")
    @Operation(summary = "Tạo đối tượng", description = "Tạo đối tượng")
    ResponseEntity<ResponseBase<I>> create(@Valid @RequestBody T request);

}
