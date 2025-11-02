package com.ptopalidis.cecloud.platform.terms.filter;


import com.ptopalidis.cecloud.platform.account.domain.CECloudv2Account;
import com.topcode.web.service.AccountService;
import com.topcode.web.service.SecurityUtilsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TermsFilter extends OncePerRequestFilter {

    private final AccountService<CECloudv2Account> accountService;
    private final SecurityUtilsService securityUtilsService;

    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.contains("/terms") || path.contains("api-docs") || path.contains("swagger");
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String userId = request.getHeader("x-user-id");

        if (userId == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return ;
        }

        if (securityUtilsService.isAdmin()) {
            chain.doFilter(request, response);
            return;
        }

        CECloudv2Account account = accountService.findByUserId(userId);

        if (!account.getHasAcceptedTerms()) {
            response.setStatus(HttpStatus.PRECONDITION_FAILED.value());
            return;
        }

        chain.doFilter(request, response);
    }

}
