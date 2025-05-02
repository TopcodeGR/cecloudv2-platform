package com.ptopalidis.cecloud.platform.exception.error;

import lombok.*;
import com.topcode.web.exception.error.Error;
import org.springframework.http.HttpStatus;

import static java.lang.String.format;


@EqualsAndHashCode(callSuper = true)
public class AlreadyExistsError extends Error {

    public AlreadyExistsError(String message){
        super("ALREADY_EXISTS",HttpStatus.CONFLICT,message);
    }

    public AlreadyExistsError(String entity, String label){
        super("ALREADY_EXISTS",HttpStatus.CONFLICT,format("%s already exists with the same name [%s]",entity,label));
    }
}
