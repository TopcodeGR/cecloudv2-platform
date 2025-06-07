package com.ptopalidis.cecloud.platform.account.mapper;

import com.ptopalidis.cecloud.platform.account.domain.CECloudv2Account;
import com.ptopalidis.cecloud.platform.account.domain.CECloudv2AccountUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CECloudv2AccountMapper {

    @Mapping(target = "logo", ignore = true)
    @Mapping(target = "signature", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    void updateAccountFromDto(CECloudv2AccountUpdateDTO dto, @MappingTarget CECloudv2Account account);
}
