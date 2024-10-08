package com.ptopalidis.cecloud.platform.common.security.aop;

import com.ptopalidis.cecloud.platform.common.exception.GlobalException;
import com.ptopalidis.cecloud.platform.common.exception.error.ResourceDoesNotBelongToUserError;
import com.ptopalidis.cecloud.platform.common.exception.error.UserDetailsNotFoundError;
import com.ptopalidis.cecloud.platform.common.security.domain.ResourceAuthorizedEntity;
import com.ptopalidis.cecloud.platform.common.security.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizationAspect {

    private final SecurityUtils securityUtils;

    @Around("@annotation(com.ptopalidis.cecloud.platform.common.security.annotation.HasAccessToResource)")
    public Object hasAccessToResource(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        ResourceAuthorizedEntity entity = (ResourceAuthorizedEntity) proceedingJoinPoint.proceed();
        Jwt jwtToken = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = securityUtils.extractUserIdFromAuthContext().orElseThrow(()-> new GlobalException(new UserDetailsNotFoundError()));

        assert entity != null;

        if(!entity.getUserId().equals(userId)){
            throw  new GlobalException(new ResourceDoesNotBelongToUserError());
        }

        return entity;
    }



}
