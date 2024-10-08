package com.ptopalidis.cecloud.platform.common.exception.error;

import org.springframework.http.HttpStatus;

public class ResourceDoesNotBelongToUserError extends Error{
    public ResourceDoesNotBelongToUserError() {
        super("RESOURCE_DOES_NOT_BELONG_TO_USER", HttpStatus.FORBIDDEN,"Resource does not belong to user");
    }
}
