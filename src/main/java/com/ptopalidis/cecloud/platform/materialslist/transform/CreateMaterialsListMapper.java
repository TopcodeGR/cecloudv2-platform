package com.ptopalidis.cecloud.platform.materialslist.transform;

import com.ptopalidis.cecloud.platform.materialslist.domain.MaterialsList;
import com.ptopalidis.cecloud.platform.materialslist.domain.dto.CreateMaterialListDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateMaterialsListMapper {

    @Mapping(target = "issuanceDate", source = "createMaterialListDto.issuanceDate")
    @Mapping(target = "productionDate", source = "createMaterialListDto.productionDate")
    @Mapping(target = "productionManager", source = "createMaterialListDto.productionManager")
    @Mapping(target = "materials", source = "createMaterialListDto.materials")
    public MaterialsList toEntity(CreateMaterialListDto createMaterialListDto);

    public CreateMaterialListDto toDTO(MaterialsList materialsList);
}


