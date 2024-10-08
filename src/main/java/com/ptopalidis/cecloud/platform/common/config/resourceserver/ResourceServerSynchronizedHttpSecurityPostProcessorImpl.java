package com.ptopalidis.cecloud.platform.common.config.resourceserver;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
public class ResourceServerSynchronizedHttpSecurityPostProcessorImpl implements  ResourceServerSynchronizedHttpSecurityPostProcessor {

    @Override
    public HttpSecurity process(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity;
    }
}
