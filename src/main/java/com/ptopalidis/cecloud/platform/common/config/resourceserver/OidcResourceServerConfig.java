package com.ptopalidis.cecloud.platform.common.config.resourceserver;

import com.ptopalidis.cecloud.platform.common.properties.OidcProperties;
import com.ptopalidis.cecloud.platform.common.properties.OpenidProviderPropertiesResolver;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Optional;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class OidcResourceServerConfig {
    @Bean
    SecurityFilterChain springAddonsJwtResourceServerSecurityFilterChain(
            HttpSecurity http,
            ServerProperties serverProperties,
            OidcProperties oidcProperties,
            ResourceServerExpressionInterceptUrlRegistryPostProcessor authorizePostProcessor,
            ResourceServerSynchronizedHttpSecurityPostProcessor httpPostProcessor,
            AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver,
            AuthenticationEntryPoint authenticationEntryPoint,
            Optional<AccessDeniedHandler> accessDeniedHandler)
            throws Exception {
        http.oauth2ResourceServer(oauth2 -> {
            oauth2.authenticationManagerResolver(authenticationManagerResolver);
            oauth2.authenticationEntryPoint(authenticationEntryPoint);
            accessDeniedHandler.ifPresent(oauth2::accessDeniedHandler);
        });

        if (oidcProperties.getResourceServer().isStatlessSessions()) {
            http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        }


        http.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);

        http.anonymous(withDefaults());
        http
            .authorizeHttpRequests(
                    registry -> registry.requestMatchers(oidcProperties.getResourceServer().getPermitAll().stream().map(AntPathRequestMatcher::new).toArray(AntPathRequestMatcher[]::new)).permitAll());
        http.authorizeHttpRequests(authorizePostProcessor::authorizeHttpRequests);
        if (serverProperties.getSsl() != null && serverProperties.getSsl().isEnabled()) {
            http.requiresChannel(channel -> channel.anyRequest().requiresSecure());
        }
        httpPostProcessor.process(http);

        return http.build();
    }

    @Bean
    AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver(
            OpenidProviderPropertiesResolver opPropertiesResolver,
            JwtDecoderFactory jwtDecoderFactory,
            Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter) {
        return new JwtAuthenticationManagerResolver(opPropertiesResolver, jwtDecoderFactory, jwtAuthenticationConverter);
    }

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer realm=\"Restricted Content\"");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        };
    }
}
