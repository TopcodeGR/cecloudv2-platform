package com.ptopalidis.cecloud.platform.ui.service;

import com.ptopalidis.cecloud.platform.account.domain.Account;
import com.ptopalidis.cecloud.platform.common.exception.GlobalException;
import com.ptopalidis.cecloud.platform.common.exception.error.AccountNotFoundError;
import com.ptopalidis.cecloud.platform.common.security.utils.SecurityUtils;
import com.ptopalidis.cecloud.platform.ui.domain.Functionality;
import com.ptopalidis.cecloud.platform.ui.repository.FunctionalityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UIService {

    private final FunctionalityRepository functionalityRepository;
    private final SecurityUtils securityUtils;

    public List<Functionality> getFunctionality() {
        Account account = securityUtils.extractAccountFromAuthContext().orElseThrow(()->new GlobalException(new AccountNotFoundError()));

        List<Functionality> allowedFunctionality = new ArrayList<>();

        List<Functionality> functionalities = functionalityRepository.findAllEnabled();


        for(Functionality functionality : functionalities) {
            if(new HashSet<>(account.getAuthorities()).containsAll(functionality.getAuthorities())){
                allowedFunctionality.add(functionality);
            }
        }

        return allowedFunctionality;
    }

}
