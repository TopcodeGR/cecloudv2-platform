package com.ptopalidis.cecloud.platform.common.config.resourceserver;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class ResourceServerExpressionInterceptUrlRegistryPostProcessorImpl implements ResourceServerExpressionInterceptUrlRegistryPostProcessor {
    @Override
    public AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizeHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        return registry.anyRequest().authenticated();
    }
}
