package com.ptopalidis.cecloud.platform.common.exception.error;

import org.springframework.http.HttpStatus;

public class UserDetailsNotFoundError extends Error {
    public UserDetailsNotFoundError() {
        super("USER_DETAILS_NOT_FOUND", HttpStatus.NOT_FOUND ,"User details not found not found");
    }

}
