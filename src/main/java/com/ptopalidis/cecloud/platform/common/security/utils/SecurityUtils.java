package com.ptopalidis.cecloud.platform.common.security.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityUtils {
    public Optional<Long> extractUserIdFromAuthContext() {
        Jwt jwtToken = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = jwtToken.getClaim("userId");
        return Optional.of(userId);
    }
}
