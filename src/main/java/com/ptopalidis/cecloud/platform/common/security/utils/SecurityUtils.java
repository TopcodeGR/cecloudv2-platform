package com.ptopalidis.cecloud.platform.common.security.utils;

import com.ptopalidis.cecloud.platform.account.domain.Account;
import com.ptopalidis.cecloud.platform.account.repository.AccountRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityUtils {

    private final AccountRepository accountRepository;


    public Optional<Account> extractAccountFromAuthContext() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();

        String userId = request.getHeader("x-user-id");
        

        Optional<Account> account = accountRepository.findByUserid(userId);

        return account;
    }
}
