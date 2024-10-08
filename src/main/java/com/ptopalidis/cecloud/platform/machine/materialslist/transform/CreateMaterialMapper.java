package com.ptopalidis.cecloud.platform.machine.materialslist.transform;

import com.ptopalidis.cecloud.platform.machine.materialslist.domain.Material;
import com.ptopalidis.cecloud.platform.machine.materialslist.domain.dto.CreateMaterialDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateMaterialMapper {

    @Mapping(target = "materialsList", ignore = true)
    Material toEntity(CreateMaterialDto createMaterialDto);
}
