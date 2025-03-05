package com.ptopalidis.cecloud.platform.exception.error;

import com.topcode.web.exception.error.Error;
import org.springframework.http.HttpStatus;

public class SerialNumberNotFoundError extends Error {

    public SerialNumberNotFoundError() {
        super("SERIAL_NUMBER_NOT_FOUND", HttpStatus.NOT_FOUND,"Serial number not found");
    }
}
