package com.ptopalidis.cecloud.platform.machine.materialslist.transform;

import com.ptopalidis.cecloud.platform.machine.materialslist.domain.MaterialsList;
import com.ptopalidis.cecloud.platform.machine.materialslist.domain.dto.UpdateMaterialListDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UpdateMaterialsListMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "materials", ignore = true)
    void updateMaterialsListFromDto(UpdateMaterialListDto updateMaterialListDto, @MappingTarget MaterialsList materialsList);
}
