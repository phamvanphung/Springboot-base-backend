package vn.kienlongbank.base.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.kienlongbank.base.core.BaseResponseData;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ListResponse<T> extends BaseResponseData {
    private List<T> items;
}
