package com.ptopalidis.cecloud.platform.machine.transform;

import com.ptopalidis.cecloud.platform.machine.domain.Machine;
import com.ptopalidis.cecloud.platform.machine.domain.dto.UpdateMachineDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UpdateMachineMapper {

    @Mapping(target = "name", source = "updateMachineDto.name")
    public Machine toEntity(UpdateMachineDto updateMachineDto);

    public UpdateMachineDto toDTO(Machine machine);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "standard", ignore = true)
    void updateMachineFromDto(UpdateMachineDto updateMachineDto, @MappingTarget Machine machine);
}
