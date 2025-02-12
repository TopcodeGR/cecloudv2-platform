package com.ptopalidis.cecloud.platform.common.exception.error;

import org.springframework.http.HttpStatus;

public class ResourceDoesNotBelongToAccountError extends Error{
    public ResourceDoesNotBelongToAccountError() {
        super("RESOURCE_DOES_NOT_BELONG_TO_ACCOUNT", HttpStatus.FORBIDDEN,"Resource does not belong to account");
    }
}
