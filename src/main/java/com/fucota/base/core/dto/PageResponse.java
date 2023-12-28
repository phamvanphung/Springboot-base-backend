package com.fucota.base.core.dto;


import com.fucota.base.core.BaseResponseData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageResponse<T> extends BaseResponseData {
    private int pageNumber = 0;
    private int pageSize = 0;
    private Long totalSize = 0L;
    private int totalPage = 0;
    private List<T> items = new ArrayList<>();

    public PageResponse(Page page) {
        if (page == null) {
            return;
        }
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.totalSize = page.getTotalElements();
        this.totalPage = page.getTotalPages();
        this.items = page.getContent();
    }

}
