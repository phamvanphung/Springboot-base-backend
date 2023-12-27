package vn.kienlongbank.base.core.crud;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.kienlongbank.base.core.BaseResponseData;
import vn.kienlongbank.base.core.ResponseBase;
import vn.kienlongbank.base.core.crud.dto.DeleteRequest;

public interface IRestDelete<T extends DeleteRequest, I extends BaseResponseData> {

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Xoá đối tượng", description = "Xoá đối tượng")
    ResponseEntity<ResponseBase<I>> delete(@PathVariable String id);

}
