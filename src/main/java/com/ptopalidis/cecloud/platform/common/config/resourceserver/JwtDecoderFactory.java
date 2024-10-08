package com.ptopalidis.cecloud.platform.common.config.resourceserver;


import org.springframework.security.oauth2.jwt.JwtDecoder;

import java.net.URI;
import java.util.Optional;


public interface JwtDecoderFactory {
    JwtDecoder create(Optional<URI> jwkSetUri, Optional<URI> issuer, Optional<String> audience);
}