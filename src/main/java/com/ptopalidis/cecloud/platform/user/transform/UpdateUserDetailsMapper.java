package com.ptopalidis.cecloud.platform.user.transform;

import com.ptopalidis.cecloud.platform.user.domain.dto.UpdateUserDetailsDto;
import com.ptopalidis.cecloud.platform.user.domain.UserDetails;
import org.mapstruct.*;

@Mapper(componentModel = "spring")

public interface UpdateUserDetailsMapper {

    public UserDetails toEntity(UpdateUserDetailsDto updateUserDetailsDto);

    public UpdateUserDetailsDto toDTO(UserDetails userDetails);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserDetailsFromDto(UpdateUserDetailsDto updateUserDetailsDto, @MappingTarget UserDetails userDetails);



}
