package com.ptopalidis.cecloud.platform.common.config.resourceserver;

import com.ptopalidis.cecloud.platform.common.properties.OpenidProviderPropertiesResolver;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.oauth2.jwt.Jwt;

public class JwtAuthenticationManagerResolver implements AuthenticationManagerResolver<HttpServletRequest> {
    private final JWTClaimsSetAuthenticationManager authenticationManager;

    public JwtAuthenticationManagerResolver(
            OpenidProviderPropertiesResolver opPropertiesResolver,
            JwtDecoderFactory jwtDecoderFactory,
            Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter) {
        this.authenticationManager = new JWTClaimsSetAuthenticationManager(opPropertiesResolver, jwtDecoderFactory, jwtAuthenticationConverter);
    }

    @Override
    public AuthenticationManager resolve(HttpServletRequest context) {
        return authenticationManager;
    }

}
