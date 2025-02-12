package com.ptopalidis.cecloud.platform.common.security.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthorityDomain {

    MACHINES("MACHINES");
    private final String domain;
}
