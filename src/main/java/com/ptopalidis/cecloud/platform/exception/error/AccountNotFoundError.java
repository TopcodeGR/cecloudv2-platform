package com.ptopalidis.cecloud.platform.exception.error;

import org.springframework.http.HttpStatus;
import com.topcode.web.exception.error.Error;

public class AccountNotFoundError extends Error {
    public AccountNotFoundError() {
        super("ACCOUNT_NOT_FOUND", HttpStatus.NOT_FOUND ,"Account not found not found");
    }

}
