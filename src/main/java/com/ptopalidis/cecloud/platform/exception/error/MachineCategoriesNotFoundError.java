package com.ptopalidis.cecloud.platform.exception.error;

import org.springframework.http.HttpStatus;
import com.topcode.web.exception.error.Error;

public class MachineCategoriesNotFoundError extends Error {

    public MachineCategoriesNotFoundError() {
        super("MACHINE_CATEGORIES_NOT_FOUND", HttpStatus.NOT_FOUND,"Machine categories not found");
    }
}
