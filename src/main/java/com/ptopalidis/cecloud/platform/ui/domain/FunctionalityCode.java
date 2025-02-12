package com.ptopalidis.cecloud.platform.ui.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FunctionalityCode {
    VIEW_MY_MACHINES("VIEW_MY_MACHINES"),
    PROCESS_MACHINE("PROCESS_MACHINE");

    private final String code;
}
