package com.ptopalidis.cecloud.platform.common.config.resourceserver;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class UnmodifiableClaimSet extends DelegatingMap<String, Object> implements ClaimSet {
    private static final long serialVersionUID = 5103156342740420106L;

    public UnmodifiableClaimSet(Map<String, Object> delegate) {
        super(Collections.unmodifiableMap(new HashMap<>(delegate)));
    }

    @Override
    public String toString() {
        return this.entrySet().stream().map(e -> String.format("%s => %s", e.getKey(), e.getValue())).collect(Collectors.joining(", ", "[", "]"));
    }
}
