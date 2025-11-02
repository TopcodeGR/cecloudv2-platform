package com.ptopalidis.cecloud.platform.terms.service;

import com.ptopalidis.cecloud.platform.account.domain.CECloudv2Account;
import com.ptopalidis.cecloud.platform.exception.error.AccountNotFoundError;
import com.topcode.web.exception.GlobalException;
import com.topcode.web.service.SecurityUtilsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class TermsService {

    private final RestTemplate restTemplate;
    private final SecurityUtilsService securityUtilsService;

    @Value( "${terms-url}" )
    private String termsUrl;

    public TermsService(RestTemplateBuilder restTemplateBuilder, SecurityUtilsService securityUtilsService) {
        this.restTemplate = restTemplateBuilder.build();
        this.securityUtilsService = securityUtilsService;
    }


    public String getTerms() {
        try {
            return restTemplate.getForObject(termsUrl, String.class);
        } catch (Exception e) {
            return "";
        }
    }

    @Transactional
    public void acceptTerms() {

        CECloudv2Account account = (CECloudv2Account) securityUtilsService
                .extractAccountFromAuthContext()
                .orElseThrow(()->new GlobalException(new AccountNotFoundError()));

        account.setHasAcceptedTerms(true);
    }

    @Transactional
    public void rejectTerms() {

        CECloudv2Account account = (CECloudv2Account) securityUtilsService
                .extractAccountFromAuthContext()
                .orElseThrow(()->new GlobalException(new AccountNotFoundError()));

        account.setHasAcceptedTerms(false);
    }
}
