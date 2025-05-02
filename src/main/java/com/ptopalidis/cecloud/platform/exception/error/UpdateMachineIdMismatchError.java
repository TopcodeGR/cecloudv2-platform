package com.ptopalidis.cecloud.platform.exception.error;

import com.topcode.web.exception.error.Error;
import org.springframework.http.HttpStatus;

public class UpdateMachineIdMismatchError extends Error{
    public UpdateMachineIdMismatchError() {
        super("UPDATE_MACHINE_ID_MISMATCH", HttpStatus.BAD_REQUEST,"Provided id does not correspond to machine");
    }
}
