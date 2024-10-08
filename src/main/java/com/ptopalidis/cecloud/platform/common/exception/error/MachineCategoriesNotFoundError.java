package com.ptopalidis.cecloud.platform.common.exception.error;

import org.springframework.http.HttpStatus;

public class MachineCategoriesNotFoundError extends Error {

    public MachineCategoriesNotFoundError() {
        super("MACHINE_CATEGORIES_NOT_FOUND", HttpStatus.NOT_FOUND,"Machine categories not found");
    }
}
