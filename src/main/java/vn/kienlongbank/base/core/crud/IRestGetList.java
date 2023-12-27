package vn.kienlongbank.base.core.crud;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import vn.kienlongbank.base.core.BaseResponseData;
import vn.kienlongbank.base.core.ResponseBase;
import vn.kienlongbank.base.core.crud.dto.GetListRequest;


public interface IRestGetList<T extends GetListRequest, I extends BaseResponseData> {

    @GetMapping("/getList")
    @Operation(summary = "Lấy danh sách đối tượng", description = "Lấy danh sách đối tượng")
    ResponseEntity<ResponseBase<I>> getList(@Valid @ParameterObject T request);

}
