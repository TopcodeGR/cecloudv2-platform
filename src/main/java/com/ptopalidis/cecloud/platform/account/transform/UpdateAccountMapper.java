package com.ptopalidis.cecloud.platform.account.transform;

import com.ptopalidis.cecloud.platform.account.domain.Account;
import com.ptopalidis.cecloud.platform.account.domain.dto.UpdateAccountDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")

public interface UpdateAccountMapper {

    public Account toEntity(UpdateAccountDto updateAccountDto);

    public UpdateAccountDto toDTO(Account account);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAccountFromDto(UpdateAccountDto updateAccountDto, @MappingTarget Account account);



}
