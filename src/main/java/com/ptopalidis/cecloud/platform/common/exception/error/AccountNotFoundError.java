package com.ptopalidis.cecloud.platform.common.exception.error;

import org.springframework.http.HttpStatus;

public class AccountNotFoundError extends Error {
    public AccountNotFoundError() {
        super("ACCOUNT_NOT_FOUND", HttpStatus.NOT_FOUND ,"Account not found not found");
    }

}
