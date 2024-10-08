package com.ptopalidis.cecloud.platform.common.config.resourceserver;

public class UnparsableClaimException extends RuntimeException {
    private static final long serialVersionUID = 5585678138757632513L;

    public UnparsableClaimException(String message) {
        super(message);
    }

}