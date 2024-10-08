package com.ptopalidis.cecloud.platform.common.exception.error;

import org.springframework.http.HttpStatus;

public class MaterialListNotFoundError extends Error{
    public MaterialListNotFoundError() {
        super("MATERIAL_LIST_NOT_FOUND", HttpStatus.NOT_FOUND,"Material list not found");
    }
}
