package com.ptopalidis.cecloud.platform.common.config.resourceserver;

import com.jayway.jsonpath.JsonPath;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ClaimSet extends Map<String, Object>, Serializable {

    default <T> T getByJsonPath(String jsonPath) {
        return JsonPath.read(this, jsonPath);
    }

    default String getAsString(String name) {
        final var claim = get(name);
        return claim == null ? null : claim.toString();
    }

    default @Nullable Instant getAsInstant(String name) {
        final var claim = get(name);
        if (claim == null) {
            return null;
        }
        if (claim instanceof final Long l) {
            return Instant.ofEpochSecond(l);
        }
        if (claim instanceof final Instant instant) {
            return instant;
        }
        if (claim instanceof final String str) {
            return Instant.parse(str);
        }
        throw new UnparsableClaimException("claim " + name + " is of unsupported type " + claim.getClass().getName());
    }

    default @Nullable Set<String> getAsStringSet(String name) {
        final var claim = get(name);
        if (claim == null) {
            return null;
        }
        if (claim instanceof final Collection<?> collection) {
            return collection.stream().flatMap(o -> Stream.of(o.toString().split(" "))).collect(Collectors.toSet());
        }
        return Stream.of(claim.toString().split(" ")).collect(Collectors.toSet());
    }

    default @Nullable URI getAsUri(String name) throws URISyntaxException {
        final var claim = get(name);
        if (claim == null) {
            return null;
        }
        if (claim instanceof final URI uri) {
            return uri;
        }
        return new URI(claim.toString());
    }

    default @Nullable Boolean getAsBoolean(String name) {
        final var claim = get(name);
        if (claim == null) {
            return null;
        }
        if (claim instanceof final Boolean b) {
            return b;
        }
        return Boolean.valueOf(claim.toString());
    }

    default ClaimSet claim(String claimName, String claimValue) {
        Assert.hasLength(claimName, "claimName can't be empty");
        if (StringUtils.hasLength(claimValue)) {
            put(claimName, claimValue);
        } else {
            remove(claimName);
        }
        return this;
    }

    default ClaimSet claim(String claimName, Collection<?> claimValue) {
        Assert.hasLength(claimName, "claimName can't be empty");
        if (claimValue == null || claimValue.isEmpty()) {
            remove(claimName);
        } else {
            put(claimName, claimValue);
        }
        return this;
    }

    default ClaimSet claim(String claimName, Object claimValue) {
        Assert.hasLength(claimName, "claimName can't be empty");
        if (claimValue == null) {
            remove(claimName);
        } else {
            put(claimName, claimValue);
        }
        return this;
    }

}
