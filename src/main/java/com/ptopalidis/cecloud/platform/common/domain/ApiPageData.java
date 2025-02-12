package com.ptopalidis.cecloud.platform.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiPageData {
    private int size;
    private int number;
    private long totalElements;
    private int totalPages;
}
