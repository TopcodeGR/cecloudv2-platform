package com.ptopalidis.cecloud.platform.common.exception.error;

import org.springframework.http.HttpStatus;

public class DocNotFoundError extends Error{
    public DocNotFoundError() {
        super("DOC_NOT_FOUND", HttpStatus.NOT_FOUND,"Declaration of performance not found");
    }
}
