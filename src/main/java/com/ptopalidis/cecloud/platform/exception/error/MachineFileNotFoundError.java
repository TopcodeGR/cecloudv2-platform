package com.ptopalidis.cecloud.platform.exception.error;

import org.springframework.http.HttpStatus;
import com.topcode.web.exception.error.Error;

public class MachineFileNotFoundError extends Error {
    public MachineFileNotFoundError() {
        super("MACHINE_FILE_NOT_FOUND", HttpStatus.NOT_FOUND ,"Machine file not found");
    }

}