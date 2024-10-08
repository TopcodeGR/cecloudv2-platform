package com.ptopalidis.cecloud.platform.common.config.resourceserver;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Component
public class DefaultJwtDecoderFactory  implements  JwtDecoderFactory{
    @Override
    public JwtDecoder create(Optional<URI> jwkSetUri, Optional<URI> issuer, Optional<String> audience) {

        final var decoder = jwkSetUri.map(uri -> NimbusJwtDecoder.withJwkSetUri(uri.toString()).build()).orElseGet(() -> NimbusJwtDecoder.withIssuerLocation(issuer.orElseThrow(InvalidJwtDecoderCreationParametersException::new).toString()).build());

        final OAuth2TokenValidator<Jwt> defaultValidator = issuer
                .map(URI::toString)
                .map(JwtValidators::createDefaultWithIssuer)
                .orElse(JwtValidators.createDefault());

        // @formatter:off
        final OAuth2TokenValidator<Jwt> jwtValidator = audience
                .filter(StringUtils::hasText)
                .map(opAudience -> new JwtClaimValidator<List<String>>(
                        JwtClaimNames.AUD,
                        (aud) -> aud != null && aud.contains(opAudience)))
                .map(audValidator -> (OAuth2TokenValidator<Jwt>) new DelegatingOAuth2TokenValidator<>(List.of(defaultValidator, audValidator)))
                .orElse(defaultValidator);
        // @formatter:on

        decoder.setJwtValidator(jwtValidator);

        return decoder;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    static class InvalidJwtDecoderCreationParametersException extends RuntimeException {
        private static final long serialVersionUID = 3575615882241560832L;

        public InvalidJwtDecoderCreationParametersException() {
            super("At least one of jwkSetUri or issuer must be provided");
        }
    }

}
