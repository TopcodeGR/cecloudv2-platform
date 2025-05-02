package com.ptopalidis.cecloud.platform.exception.error;

import org.springframework.http.HttpStatus;
import com.topcode.web.exception.error.Error;

public class DocNotFoundError extends Error{
    public DocNotFoundError() {
        super("DOC_NOT_FOUND", HttpStatus.NOT_FOUND,"Declaration of performance not found");
    }
}
