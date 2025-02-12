package com.ptopalidis.cecloud.platform.common.exception.error;

import org.springframework.http.HttpStatus;

public class InsufficientAuthoritiesError extends Error{
    public InsufficientAuthoritiesError() {
        super("INSUFFICIENT_AUTHORITIES", HttpStatus.FORBIDDEN,"Account has insufficient authorities");
    }
}

