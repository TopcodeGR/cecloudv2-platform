package com.ptopalidis.cecloud.platform.common.exception;


import com.ptopalidis.cecloud.platform.common.exception.error.Error;
import lombok.Getter;

import java.util.List;

@Getter
public class GlobalException extends RuntimeException {

    private final Error error;

    private final Object[] data;


    public GlobalException(Error error){
        this(error, List.of());
    }

    public GlobalException(Error error, Object... data){
        this(error,null, data);
    }

    public GlobalException(Error error, Throwable cause, Object... data){
        super(error.getMessage(), cause);
        this.error = error;
        this.data =  data;
    }
}
