package com.fucota.base.core.crud;

import com.fucota.base.core.BaseResponseData;
import com.fucota.base.core.crud.dto.UpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.fucota.base.core.ResponseBase;

public interface IRestUpdate<T extends UpdateRequest, I extends BaseResponseData> {

    @PutMapping("/update")
    @Operation(summary = "Cập nhật đối tượng", description = "Cập nhật đối tượng")
    ResponseEntity<ResponseBase<I>> update(@Valid @RequestBody T request);

}
