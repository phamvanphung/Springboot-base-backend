package vn.kienlongbank.base.core.crud;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vn.kienlongbank.base.core.BaseResponseData;
import vn.kienlongbank.base.core.ResponseBase;
import vn.kienlongbank.base.core.crud.dto.UpdateRequest;

public interface IRestUpdate<T extends UpdateRequest, I extends BaseResponseData> {

    @PutMapping("/update")
    @Operation(summary = "Cập nhật đối tượng", description = "Cập nhật đối tượng")
    ResponseEntity<ResponseBase<I>> update(@Valid @RequestBody T request);

}
