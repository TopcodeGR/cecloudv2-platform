package com.ptopalidis.cecloud.platform.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Data
@ConfigurationProperties("oidc.client")
public class OidcClientProperties {
    public static final String RESPONSE_STATUS_HEADER = "X-RESPONSE-STATUS";

    public static final String POST_AUTHENTICATION_SUCCESS_URI_HEADER = "X-POST-LOGIN-SUCCESS-URI";
    public static final String POST_AUTHENTICATION_SUCCESS_URI_PARAM = "post_login_success_uri";
    public static final String POST_AUTHENTICATION_SUCCESS_URI_SESSION_ATTRIBUTE = POST_AUTHENTICATION_SUCCESS_URI_PARAM;

    public static final String POST_AUTHENTICATION_FAILURE_URI_HEADER = "X-POST-LOGIN-FAILURE-URI";
    public static final String POST_AUTHENTICATION_FAILURE_URI_PARAM = "post_login_failure_uri";
    public static final String POST_AUTHENTICATION_FAILURE_URI_SESSION_ATTRIBUTE = POST_AUTHENTICATION_FAILURE_URI_PARAM;
    public static final String POST_AUTHENTICATION_FAILURE_CAUSE_ATTRIBUTE = "error";

    public static final String POST_LOGOUT_SUCCESS_URI_HEADER = "X-POST-LOGOUT-SUCCESS-URI";
    public static final String POST_LOGOUT_SUCCESS_URI_PARAM = "post_logout_success_uri";

    private List<String> securityMatchers = List.of();
    private URI clientUri = URI.create("/");
    private Optional<URI> postLoginRedirectHost = Optional.empty();
    private OAuth2RedirectionProperties oauth2Redirections = new OAuth2RedirectionProperties();
    private Csrf csrf = Csrf.DEFAULT;
    private List<String> permitAll = List.of("/login/**", "/oauth2/**");


    @Data
    @ConfigurationProperties
    public static class OAuth2RedirectionProperties {
        private HttpStatus rpInitiatedLogout = HttpStatus.FOUND;
        private HttpStatus preAuthorizationCode = HttpStatus.FOUND;
        private HttpStatus postAuthorizationCode = HttpStatus.FOUND;
    }


}
