package com.ptopalidis.cecloud.platform.common.exception.error;

import org.springframework.http.HttpStatus;

public class TemplateParamsProviderNotFoundError extends Error {

    public TemplateParamsProviderNotFoundError() {
        super("TEMPLATE_PARAMS_PROVIDER_NOT_FOUND_ERROR", HttpStatus.NOT_FOUND,"Template params provider not found");
    }
}
