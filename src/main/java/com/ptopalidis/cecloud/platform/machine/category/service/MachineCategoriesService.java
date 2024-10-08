package com.ptopalidis.cecloud.platform.machine.category.service;

import com.ptopalidis.cecloud.platform.common.exception.GlobalException;
import com.ptopalidis.cecloud.platform.common.exception.error.AlreadyExistsError;
import com.ptopalidis.cecloud.platform.machine.category.domain.MachineCategory;
import com.ptopalidis.cecloud.platform.machine.category.domain.dto.CreateMachineCategoryDto;
import com.ptopalidis.cecloud.platform.machine.category.repository.MachineCategoryRepository;
import com.ptopalidis.cecloud.platform.machine.category.transform.CreateMachineCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class MachineCategoriesService {

    private final MachineCategoryRepository machineCategoryRepository;

    private final CreateMachineCategoryMapper createMachineCategoryMapper;


    public Page<MachineCategory> findAllMachineCategories(Pageable p){
        return  this.machineCategoryRepository.findAll(p);
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
