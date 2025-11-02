package com.ptopalidis.cecloud.platform.account.domain;

import com.topcode.web.domain.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CECloudv2Account extends Account {

    @Column(name = "afm")
    private String afm;

    @Column(name = "doi")
    private String doi;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "address_number")
    private String addressNumber;

    @Column(name = "phone")
    private String phone;

    @Column(name = "logo")
    private String logo;

    @Column(name = "signature")
    private String signature;

    @Column(name = "has_accepted_terms")
    private Boolean hasAcceptedTerms;
}

