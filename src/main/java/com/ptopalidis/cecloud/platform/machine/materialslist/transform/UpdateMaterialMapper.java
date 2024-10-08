package com.ptopalidis.cecloud.platform.machine.materialslist.transform;


import com.ptopalidis.cecloud.platform.machine.materialslist.domain.Material;
import com.ptopalidis.cecloud.platform.machine.materialslist.domain.dto.UpdateMaterialDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UpdateMaterialMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "materialsList", ignore = true)
    void updateMaterialFromDto(UpdateMaterialDto updateMaterialDto, @MappingTarget Material material);

    @Mapping(target = "materialsList", ignore = true)
    Material toEntity(UpdateMaterialDto updateMaterialDto);
}
