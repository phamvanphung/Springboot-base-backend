package com.fucota.base.core.crud;

import com.fucota.base.core.crud.dto.DeleteRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.fucota.base.core.BaseResponseData;
import com.fucota.base.core.ResponseBase;

public interface IRestDelete<T extends DeleteRequest, I extends BaseResponseData> {

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Xoá đối tượng", description = "Xoá đối tượng")
    ResponseEntity<ResponseBase<I>> delete(@PathVariable String id);

}
