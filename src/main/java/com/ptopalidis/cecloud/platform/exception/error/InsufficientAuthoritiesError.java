package com.ptopalidis.cecloud.platform.exception.error;

import org.springframework.http.HttpStatus;
import com.topcode.web.exception.error.Error;

public class InsufficientAuthoritiesError extends Error{
    public InsufficientAuthoritiesError() {
        super("INSUFFICIENT_AUTHORITIES", HttpStatus.FORBIDDEN,"Account has insufficient authorities");
    }
}

