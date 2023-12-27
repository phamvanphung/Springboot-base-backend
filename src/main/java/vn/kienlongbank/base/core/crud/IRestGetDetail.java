package vn.kienlongbank.base.core.crud;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.kienlongbank.base.core.BaseResponseData;
import vn.kienlongbank.base.core.ResponseBase;
import vn.kienlongbank.base.core.crud.dto.GetDetailRequest;

public interface IRestGetDetail<T extends GetDetailRequest, I extends BaseResponseData> {

    @GetMapping("/getDetail/{id}")
    @Operation(summary = "Lấy chi tiết đối tượng", description = "Lấy chi tiết đối tượng")
    ResponseEntity<ResponseBase<I>> getDetail(@PathVariable String id);

}
