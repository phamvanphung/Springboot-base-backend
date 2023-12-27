package vn.kienlongbank.base.core.dto;

import lombok.*;
import vn.kienlongbank.base.core.BaseResponseData;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse extends BaseResponseData {
    private boolean success = true;
}
