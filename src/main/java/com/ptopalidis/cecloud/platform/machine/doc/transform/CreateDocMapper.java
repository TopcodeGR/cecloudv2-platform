package com.ptopalidis.cecloud.platform.machine.doc.transform;

import com.ptopalidis.cecloud.platform.machine.doc.domain.Doc;
import com.ptopalidis.cecloud.platform.machine.doc.domain.dto.CreateDocDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateDocMapper {

    @Mapping(target = "issuanceDate", source = "createDocDto.issuanceDate")
    @Mapping(target = "productionDate", source = "createDocDto.productionDate")
    @Mapping(target = "productionManager", source = "createDocDto.productionManager")
    public Doc toEntity(CreateDocDto createDocDto);

    public CreateDocDto toDTO(Doc doc);

}