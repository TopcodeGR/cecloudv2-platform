package com.ptopalidis.cecloud.platform.common.config.resourceserver;


import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.ptopalidis.cecloud.platform.common.properties.NotAConfiguredOpenidProviderException;
import com.ptopalidis.cecloud.platform.common.properties.OidcProperties;
import com.ptopalidis.cecloud.platform.common.properties.OpenidProviderPropertiesResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class ConfigurableClaimSetAuthoritiesConverter implements ClaimSetAuthoritiesConverter {
    private final OpenidProviderPropertiesResolver opPropertiesResolver;

    @Override
    public Collection<? extends GrantedAuthority> convert(@NonNull Map<String, Object> source) {
        final var opProperties = opPropertiesResolver.resolve(source).orElseThrow(() -> new NotAConfiguredOpenidProviderException(source));
        // @formatter:off
        return opProperties.getAuthorities().stream()
                .flatMap(authoritiesMappingProps -> getAuthorities(source, authoritiesMappingProps))
                .map(r -> (GrantedAuthority) new SimpleGrantedAuthority(r)).toList();
        // @formatter:on
    }

    private static String processCase(String role, OidcProperties.OpenidProviderProperties.SimpleAuthoritiesMappingProperties.Case caze) {
        switch (caze) {
            case UPPER: {
                return role.toUpperCase();
            }
            case LOWER: {
                return role.toLowerCase();
            }
            default:
                return role;
        }
    }

    private static Stream<String> getAuthorities(Map<String, Object> claims, OidcProperties.OpenidProviderProperties.SimpleAuthoritiesMappingProperties props) {
        // @formatter:off
        return getClaims(claims, props.getPath())
                .flatMap(claim -> Stream.of(claim.split(",")))
                .flatMap(claim -> Stream.of(claim.split(" ")))
                .filter(StringUtils::hasText)
                .map(String::trim)
                .map(r -> processCase(r, props.getCaze()))
                .map(r -> String.format("%s%s", props.getPrefix(), r));
        // @formatter:on
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Stream<String> getClaims(Map<String, Object> claims, String path) {
        try {
            final var res = JsonPath.read(claims, path);
            if (res instanceof String r) {
                return Stream.of(r);
            }
            if (res instanceof List l) {
                if (l.size() == 0) {
                    return Stream.empty();
                }
                if (l.get(0) instanceof String) {
                    return l.stream();
                }
                if (l.get(0) instanceof List) {
                    return l.stream().flatMap(o -> ((List) o).stream());
                }
            }
            return Stream.empty();
        } catch (PathNotFoundException e) {
            return Stream.empty();
        }
    }
}

