package com.ptopalidis.cecloud.platform.pcd.transform;

import com.ptopalidis.cecloud.platform.pcd.domain.Pcd;
import com.ptopalidis.cecloud.platform.pcd.domain.dto.CreatePcdDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreatePcdMapper {
    @Mapping(target = "productionStartDate", source = "createPcdDto.productionStartDate")
    @Mapping(target = "productionManager", source = "createPcdDto.productionManager")
    @Mapping(target = "orderNumber", source = "createPcdDto.orderNumber")
    @Mapping(target = "quantity", source = "createPcdDto.quantity")
    @Mapping(target = "cuttingProductionStartDate", source = "createPcdDto.cuttingProductionStartDate")
    @Mapping(target = "weldingProductionStartDate", source = "createPcdDto.weldingProductionStartDate")
    @Mapping(target = "moddingProductionStartDate", source = "createPcdDto.moddingProductionStartDate")
    public Pcd toEntity(CreatePcdDto createPcdDto);

    public CreatePcdDto toDTO(Pcd pcd);

}
