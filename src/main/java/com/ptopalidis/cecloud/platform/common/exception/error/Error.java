package com.ptopalidis.cecloud.platform.common.exception.error;

import lombok.*;

import org.springframework.http.HttpStatus;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class Error {

    private  String code;
    private  HttpStatus httpStatus;
    private  String message;

}
