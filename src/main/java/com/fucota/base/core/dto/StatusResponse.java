package com.fucota.base.core.dto;

import com.fucota.base.core.BaseResponseData;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse extends BaseResponseData {
    private boolean success = true;
}
