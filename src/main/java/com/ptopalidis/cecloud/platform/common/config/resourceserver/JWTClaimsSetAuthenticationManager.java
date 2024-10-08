package com.ptopalidis.cecloud.platform.common.config.resourceserver;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.ptopalidis.cecloud.platform.common.properties.NotAConfiguredOpenidProviderException;
import com.ptopalidis.cecloud.platform.common.properties.OpenidProviderPropertiesResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.util.Assert;

import java.net.URI;
import java.text.ParseException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class JWTClaimsSetAuthenticationManager implements AuthenticationManager {
    private final JWTClaimsSetAuthenticationManagerResolver jwtAuthenticationManagerResolver;

    public JWTClaimsSetAuthenticationManager(
            OpenidProviderPropertiesResolver opPropertiesResolver,
            JwtDecoderFactory jwtDecoderFactory,
            Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter) {
        this.jwtAuthenticationManagerResolver = new JWTClaimsSetAuthenticationManagerResolver(
                opPropertiesResolver,
                jwtDecoderFactory,
                jwtAuthenticationConverter);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isTrue(authentication instanceof BearerTokenAuthenticationToken, "Authentication must be of type BearerTokenAuthenticationToken");
        JWTClaimsSet jwtClaimSet;
        try {
            jwtClaimSet = JWTParser.parse(((BearerTokenAuthenticationToken) authentication).getToken()).getJWTClaimsSet();
        } catch (ParseException e) {
            throw new InvalidBearerTokenException("Could not retrieve JWT claim-set");
        }
        AuthenticationManager authenticationManager = this.jwtAuthenticationManagerResolver.resolve(jwtClaimSet);
        if (authenticationManager == null) {
            throw new InvalidBearerTokenException("Could not resolve the authentication manager for the provided JWT");
        }
        return authenticationManager.authenticate(authentication);
    }


    @RequiredArgsConstructor
    public static class JWTClaimsSetAuthenticationManagerResolver implements AuthenticationManagerResolver<JWTClaimsSet> {

        private final OpenidProviderPropertiesResolver opPropertiesResolver;
        private final JwtDecoderFactory jwtDecoderFactory;
        private final Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter;
        private final Map<String, AuthenticationManager> jwtManagers = new ConcurrentHashMap<>();

        @Override
        public AuthenticationManager resolve(JWTClaimsSet jwt) {
            final var issuer = jwt.getIssuer();
            if (!jwtManagers.containsKey(issuer)) {
                final var opProperties = opPropertiesResolver
                        .resolve(jwt.getClaims())
                        .orElseThrow(() -> new NotAConfiguredOpenidProviderException(jwt.getClaims()));

                final var decoder = jwtDecoderFactory
                        .create(
                                Optional.ofNullable(opProperties.getJwkSetUri()),
                                Optional.of(URI.create(jwt.getIssuer())),
                                Optional.ofNullable(opProperties.getAud()));

                var provider = new JwtAuthenticationProvider(decoder);
                provider.setJwtAuthenticationConverter(jwtAuthenticationConverter);
                jwtManagers.put(issuer, provider::authenticate);
            }
            return jwtManagers.get(issuer);
        }
    }

}
