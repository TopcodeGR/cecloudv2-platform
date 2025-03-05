package com.ptopalidis.cecloud.platform.exception.error;

import com.topcode.web.exception.error.Error;
import org.springframework.http.HttpStatus;

public class MaterialListNotFoundError extends Error{
    public MaterialListNotFoundError() {
        super("MATERIAL_LIST_NOT_FOUND", HttpStatus.NOT_FOUND,"Material list not found");
    }
}
