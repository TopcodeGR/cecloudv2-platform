package com.ptopalidis.cecloud.platform.common.exception.error;

import org.springframework.http.HttpStatus;

public class SerialNumberNotFoundError extends Error {

    public SerialNumberNotFoundError() {
        super("SERIAL_NUMBER_NOT_FOUND", HttpStatus.NOT_FOUND,"Serial number not found");
    }
}
