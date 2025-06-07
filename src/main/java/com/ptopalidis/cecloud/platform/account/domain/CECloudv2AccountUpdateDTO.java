package com.ptopalidis.cecloud.platform.account.domain;

import com.topcode.web.domain.AccountUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CECloudv2AccountUpdateDTO extends AccountUpdateDTO {

    private String afm;

    private String doi;

    private String businessName;

    private String country;

    private String city;

    private String address;

    private String addressNumber;

    private String phone;

    private String logo;

    private String signature;
}
