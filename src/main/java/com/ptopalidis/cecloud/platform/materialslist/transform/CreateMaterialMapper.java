package com.ptopalidis.cecloud.platform.materialslist.transform;

import com.ptopalidis.cecloud.platform.materialslist.domain.Material;
import com.ptopalidis.cecloud.platform.materialslist.domain.dto.CreateMaterialDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateMaterialMapper {

    @Mapping(target = "materialsList", ignore = true)
    Material toEntity(CreateMaterialDto createMaterialDto);
}
