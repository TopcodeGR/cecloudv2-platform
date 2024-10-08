package com.ptopalidis.cecloud.platform.common.config.resourceserver;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;

public interface ClaimSetAuthoritiesConverter extends Converter<Map<String, Object>, Collection<? extends GrantedAuthority>> {

}