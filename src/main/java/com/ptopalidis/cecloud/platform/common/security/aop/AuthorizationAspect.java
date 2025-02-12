package com.ptopalidis.cecloud.platform.common.security.aop;

import com.ptopalidis.cecloud.platform.account.domain.Account;
import com.ptopalidis.cecloud.platform.common.exception.GlobalException;
import com.ptopalidis.cecloud.platform.common.exception.error.AccountNotFoundError;
import com.ptopalidis.cecloud.platform.common.exception.error.InsufficientAuthoritiesError;
import com.ptopalidis.cecloud.platform.common.exception.error.ResourceDoesNotBelongToAccountError;
import com.ptopalidis.cecloud.platform.common.security.annotation.RequiredAuthorities;
import com.ptopalidis.cecloud.platform.common.security.domain.Authority;
import com.ptopalidis.cecloud.platform.common.security.domain.AuthorityCode;
import com.ptopalidis.cecloud.platform.common.security.domain.ResourceAuthorizedEntity;
import com.ptopalidis.cecloud.platform.common.security.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizationAspect {

    private final SecurityUtils securityUtils;

    @Around("@annotation(com.ptopalidis.cecloud.platform.common.security.annotation.RequiredAuthorities)")
    public Object requiredAuthorities(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {


        Account account = securityUtils.extractAccountFromAuthContext().orElseThrow(()-> new GlobalException(new AccountNotFoundError()));
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        RequiredAuthorities annotation = method.getAnnotation(RequiredAuthorities.class);

        List<AuthorityCode> requiredAuthorities = List.of(annotation.authorities());

        if (!account.getAuthorities().stream().map(Authority::getAuthorityCode).collect(Collectors.toSet()).containsAll(requiredAuthorities)) {
            throw new GlobalException(new InsufficientAuthoritiesError());
        }

        return proceedingJoinPoint.proceed();

    }


    @Around("@annotation(com.ptopalidis.cecloud.platform.common.security.annotation.HasAccessToResource)")
    public Object hasAccessToResource(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        ResourceAuthorizedEntity entity = (ResourceAuthorizedEntity) proceedingJoinPoint.proceed();
        Account account = securityUtils.extractAccountFromAuthContext().orElseThrow(()-> new GlobalException(new AccountNotFoundError()));

        assert entity != null;

        if(!entity.getAccountId().equals(account.getId())){
            throw  new GlobalException(new ResourceDoesNotBelongToAccountError());
        }

        return entity;
    }



}
