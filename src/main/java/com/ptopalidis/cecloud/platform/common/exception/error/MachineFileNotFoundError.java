package com.ptopalidis.cecloud.platform.common.exception.error;

import org.springframework.http.HttpStatus;

public class MachineFileNotFoundError extends Error {
    public MachineFileNotFoundError() {
        super("MACHINE_FILE_NOT_FOUND", HttpStatus.NOT_FOUND ,"Machine file not found");
    }

}