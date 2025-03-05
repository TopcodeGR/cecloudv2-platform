package com.ptopalidis.cecloud.platform.materialslist.service;

import com.ptopalidis.cecloud.platform.exception.error.MaterialListNotFoundError;
import com.ptopalidis.cecloud.platform.exception.error.SerialNumberNotFoundError;
import com.ptopalidis.cecloud.platform.materialslist.domain.Material;
import com.ptopalidis.cecloud.platform.materialslist.domain.MaterialsList;
import com.ptopalidis.cecloud.platform.materialslist.domain.dto.CreateMaterialListDto;
import com.ptopalidis.cecloud.platform.materialslist.domain.dto.UpdateMaterialDto;
import com.ptopalidis.cecloud.platform.materialslist.domain.dto.UpdateMaterialListDto;
import com.ptopalidis.cecloud.platform.materialslist.repository.MaterialsListRepository;
import com.ptopalidis.cecloud.platform.materialslist.transform.CreateMaterialMapper;
import com.ptopalidis.cecloud.platform.materialslist.transform.CreateMaterialsListMapper;
import com.ptopalidis.cecloud.platform.materialslist.transform.UpdateMaterialMapper;
import com.ptopalidis.cecloud.platform.materialslist.transform.UpdateMaterialsListMapper;
import com.ptopalidis.cecloud.platform.serialnumber.domain.SerialNumber;
import com.ptopalidis.cecloud.platform.serialnumber.repository.SerialNumberRepository;
import com.topcode.pdfgenerator.service.PdfGeneratorService;
import com.topcode.web.annotation.HasAccessToResource;
import com.topcode.web.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MaterialsListService {

    private final MaterialsListRepository materialsListRepository;
    private final SerialNumberRepository serialNumberRepository;
    private final CreateMaterialsListMapper createMaterialsListMapper;
    private final UpdateMaterialsListMapper updatedMaterialsListMapper;
    private final CreateMaterialMapper createMaterialMapper;
    private final UpdateMaterialMapper updateMaterialMapper;
    private final PdfGeneratorService pdfGeneratorService;
    private final MaterialsListTemplateParamsProvider materialsListTemplateParamsProvider;

    @HasAccessToResource
    public MaterialsList getMaterialsListBySerialNumber(Long serialNumberId) {
        return materialsListRepository.findBySerialNumberId(serialNumberId)
                .orElseThrow(()->new GlobalException(new MaterialListNotFoundError()));
    }


    @Transactional
    @HasAccessToResource
    public MaterialsList createMaterialsList(Long serialNumberId, CreateMaterialListDto createMaterialListDto){
        SerialNumber serialNumber = serialNumberRepository.findById(serialNumberId).orElseThrow(()->new GlobalException(new SerialNumberNotFoundError()));

        MaterialsList materialsList = createMaterialsListMapper.toEntity(createMaterialListDto);

        for (Material material : materialsList.getMaterials()) {
            material.setMaterialsList(materialsList);
        }

        materialsList.setSerialNumber(serialNumber);

        return materialsListRepository.save(materialsList);
    }

    @HasAccessToResource
    @Transactional
    public MaterialsList updateMaterialsList(Long serialNumberId, UpdateMaterialListDto updateMaterialListDto){

        MaterialsList materialsList = materialsListRepository.findBySerialNumberId(serialNumberId)
                .orElseThrow(()->new GlobalException(new MaterialListNotFoundError()));

        updatedMaterialsListMapper.updateMaterialsListFromDto(updateMaterialListDto, materialsList);

        if (!Objects.isNull(updateMaterialListDto.getMaterials())) {
            List<Material> updatedMaterials = new ArrayList<>();
            for (UpdateMaterialDto updateMaterialDto : updateMaterialListDto.getMaterials()){
                Material material = materialsList.getMaterials().stream()
                        .filter(m -> m.getId().equals(updateMaterialDto.getId()))
                        .findAny()
                        .orElse(null);
                if (!Objects.isNull(material)){
                    updateMaterialMapper.updateMaterialFromDto(updateMaterialDto, material);
                    updatedMaterials.add(material);
                } else {
                    updatedMaterials.add(updateMaterialMapper.toEntity(updateMaterialDto));
                }
            }

            for (Material material : updatedMaterials) {
                material.setMaterialsList(materialsList);
            }

            materialsList.setMaterials(updatedMaterials);
        }

        return materialsListRepository.save(materialsList);
    }

}
