package com.ptopalidis.cecloud.platform.category.service;

import com.ptopalidis.cecloud.platform.exception.error.AlreadyExistsError;
import com.ptopalidis.cecloud.platform.category.domain.MachineCategory;
import com.ptopalidis.cecloud.platform.category.domain.dto.CreateMachineCategoryDto;
import com.ptopalidis.cecloud.platform.category.repository.MachineCategoryRepository;
import com.ptopalidis.cecloud.platform.category.transform.CreateMachineCategoryMapper;
import com.topcode.web.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class MachineCategoriesService {

    private final MachineCategoryRepository machineCategoryRepository;

    private final CreateMachineCategoryMapper createMachineCategoryMapper;


    public List<MachineCategory> findAllMachineCategories(){
        return  this.machineCategoryRepository.findAll();
    }

    @Transactional
    public Boolean deleteMachineCategoryById(Long id){
        this.machineCategoryRepository.deleteById(id);
        return true;
    }

    @Transactional
    public MachineCategory saveMachineCategory(CreateMachineCategoryDto createMachineCategoryDto){
        if(this.machineCategoryRepository.findMachineCategoryByName(createMachineCategoryDto.getName()).isPresent()){
            throw new GlobalException(new AlreadyExistsError(format("Name %s is already used", createMachineCategoryDto.getName())));
        }

        MachineCategory machineCategory = createMachineCategoryMapper.toEntity(createMachineCategoryDto);

        machineCategory = machineCategoryRepository.save(machineCategory);

        return machineCategory;
    }

}
