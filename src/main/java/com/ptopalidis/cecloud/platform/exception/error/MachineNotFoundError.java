package com.ptopalidis.cecloud.platform.exception.error;

import com.topcode.web.exception.error.Error;
import org.springframework.http.HttpStatus;

public class MachineNotFoundError extends Error{
    public MachineNotFoundError() {
        super("MACHINE_NOT_FOUND", HttpStatus.NOT_FOUND,"Machine not found");
    }

}
