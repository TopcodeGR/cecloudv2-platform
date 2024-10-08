package com.ptopalidis.cecloud.platform.machine.category.transform;

import com.ptopalidis.cecloud.platform.machine.category.domain.dto.CreateMachineCategoryDto;
import com.ptopalidis.cecloud.platform.machine.category.domain.MachineCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateMachineCategoryMapper {
    @Mapping(target = "name", source = "createMachineCategoryDto.name")
    public MachineCategory toEntity(CreateMachineCategoryDto createMachineCategoryDto);

    public CreateMachineCategoryDto toDTO(MachineCategory machineCategory);
}
