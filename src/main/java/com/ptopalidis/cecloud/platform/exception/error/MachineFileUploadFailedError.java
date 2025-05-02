package com.ptopalidis.cecloud.platform.exception.error;

import org.springframework.http.HttpStatus;
import com.topcode.web.exception.error.Error;

public class MachineFileUploadFailedError extends Error {

    public MachineFileUploadFailedError() {
        super("MACHINE_FILE_UPLOAD_FAILED", HttpStatus.SERVICE_UNAVAILABLE,"Machine file upload failed, please try again later");
    }
}
