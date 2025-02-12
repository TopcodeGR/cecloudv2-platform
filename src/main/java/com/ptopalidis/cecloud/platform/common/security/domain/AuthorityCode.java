package com.ptopalidis.cecloud.platform.common.security.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthorityCode {
    GET_MACHINES("GET_MACHINES"),
    GET_MACHINE("GET_MACHINE"),
    UPDATE_MACHINE("UPDATE_MACHINE"),

    GET_MACHINE_CATEGORIES("GET_MACHINE_CATEGORIES"),
    UPLOAD_MACHINE_FILE("UPLOAD_MACHINE_FILE"),
    DELETE_MACHINE_FILE("DELETE_MACHINE_FILE"),;

    private final String code;
}
