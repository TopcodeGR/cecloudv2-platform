package com.ptopalidis.cecloud.platform.common.exception.error;

import org.springframework.http.HttpStatus;

public class MachineFileUploadFailedError extends Error {

    public MachineFileUploadFailedError() {
        super("MACHINE_FILE_UPLOAD_FAILED", HttpStatus.SERVICE_UNAVAILABLE,"Machine file upload failed, please try again later");
    }
}
