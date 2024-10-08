package com.ptopalidis.cecloud.platform.common.config.resourceserver;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

public interface JwtAbstractAuthenticationTokenConverter extends Converter<Jwt, AbstractAuthenticationToken> {

}