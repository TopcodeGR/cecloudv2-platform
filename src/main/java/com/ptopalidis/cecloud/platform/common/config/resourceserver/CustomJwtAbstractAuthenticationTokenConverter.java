package com.ptopalidis.cecloud.platform.common.config.resourceserver;


import com.ptopalidis.cecloud.platform.common.properties.NotAConfiguredOpenidProviderException;
import com.ptopalidis.cecloud.platform.common.properties.OpenidProviderPropertiesResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomJwtAbstractAuthenticationTokenConverter implements JwtAbstractAuthenticationTokenConverter {

    private final Converter<Map<String, Object>, Collection<? extends GrantedAuthority>> authoritiesConverter;
    private final OpenidProviderPropertiesResolver opPropertiesResolver;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        return new JwtAuthenticationToken(
                jwt,
                authoritiesConverter.convert(jwt.getClaims()),
                new OpenidClaimSet(
                        jwt.getClaims(),
                        opPropertiesResolver.resolve(jwt.getClaims()).orElseThrow(() -> new NotAConfiguredOpenidProviderException(jwt.getClaims())).getUsernameClaim())
                        .getName());
    }

    @Override
    public <U> Converter<Jwt, U> andThen(Converter<? super AbstractAuthenticationToken, ? extends U> after) {
        return JwtAbstractAuthenticationTokenConverter.super.andThen(after);
    }
}
