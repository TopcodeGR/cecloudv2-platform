package com.ptopalidis.cecloud.platform.common.config.resourceserver;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface SynchronizedHttpSecurityPostProcessor {
    HttpSecurity process(HttpSecurity httpSecurity) throws Exception;
}