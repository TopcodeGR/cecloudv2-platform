package com.ptopalidis.cecloud.platform.common.security.annotation;

import com.ptopalidis.cecloud.platform.common.security.domain.AuthorityCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequiredAuthorities {

    AuthorityCode[] authorities() default {};
}
