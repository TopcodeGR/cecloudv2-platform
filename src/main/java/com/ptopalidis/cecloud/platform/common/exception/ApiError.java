package com.ptopalidis.cecloud.platform.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
public class ApiError {

    private String message;
    private Integer status;
    private Date timestamp;
    private String code;

    private Object[] data;
}
