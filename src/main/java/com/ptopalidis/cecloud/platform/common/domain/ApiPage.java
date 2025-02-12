package com.ptopalidis.cecloud.platform.common.domain;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;


@Data
public class ApiPage<T> {

    private List<T> content;
    private ApiPageData pageData;

    public ApiPage(Page<T> page) {
        this.pageData = new ApiPageData(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
        this.content = page.getContent();
    }
}
