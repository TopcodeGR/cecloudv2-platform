package com.ptopalidis.cecloud.platform.common.properties;

import java.util.Map;
import java.util.Optional;

public interface OpenidProviderPropertiesResolver {
    Optional<OidcProperties.OpenidProviderProperties> resolve(Map<String, Object> claimSet);
}
