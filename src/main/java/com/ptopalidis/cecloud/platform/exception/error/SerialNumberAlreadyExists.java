package com.ptopalidis.cecloud.platform.exception.error;

import com.topcode.web.exception.error.Error;
import org.springframework.http.HttpStatus;

public class SerialNumberAlreadyExists extends  Error {
    public SerialNumberAlreadyExists() {
        super("SERIAL_NUMBER_ALREADY_EXISTS", HttpStatus.CONFLICT,"Serial number already exists");
    }
}
