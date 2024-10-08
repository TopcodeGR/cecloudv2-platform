package com.ptopalidis.cecloud.platform.common.exception.error;

import org.springframework.http.HttpStatus;

public class TemplateNotFoundError  extends Error {

    public TemplateNotFoundError() {
        super("TEMPLATE_NOT_FOUND_ERROR", HttpStatus.NOT_FOUND,"Template not found");
    }
}
