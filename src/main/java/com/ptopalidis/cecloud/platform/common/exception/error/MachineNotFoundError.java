package com.ptopalidis.cecloud.platform.common.exception.error;

import org.springframework.http.HttpStatus;


public class MachineNotFoundError extends Error{
    public MachineNotFoundError() {
        super("MACHINE_NOT_FOUND", HttpStatus.NOT_FOUND,"Machine not found");
    }

}
