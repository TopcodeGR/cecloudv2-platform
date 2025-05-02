package com.ptopalidis.cecloud.platform.serialnumber.transform;

import com.ptopalidis.cecloud.platform.serialnumber.domain.SerialNumber;
import com.ptopalidis.cecloud.platform.serialnumber.domain.dto.CreateSerialNumberDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")

public interface CreateSerialNumberMapper {

    @Mapping(target = "sn", source = "createSerialNumberDto.sn")
    public SerialNumber toEntity(CreateSerialNumberDto createSerialNumberDto);

    public CreateSerialNumberDto toDTO(SerialNumber serialNumber);
}
