package com.ptopalidis.cecloud.platform.machine.service;

import com.ptopalidis.cecloud.platform.common.exception.GlobalException;
import com.ptopalidis.cecloud.platform.common.exception.error.MachineNotFoundError;
import com.ptopalidis.cecloud.platform.common.exception.error.UserDetailsNotFoundError;
import com.ptopalidis.cecloud.platform.common.security.annotation.HasAccessToResource;
import com.ptopalidis.cecloud.platform.common.security.utils.SecurityUtils;
import com.ptopalidis.cecloud.platform.machine.domain.Machine;
import com.ptopalidis.cecloud.platform.machine.domain.dto.UpdateMachineDto;
import com.ptopalidis.cecloud.platform.machine.repository.MachineRepository;
import com.ptopalidis.cecloud.platform.machine.transform.UpdateMachineMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MachinesService {

    private final MachineRepository machineRepository;
    private final UpdateMachineMapper updateMachineMapper;
    private final SecurityUtils securityUtils;

    public Page<Machine> findAll(Pageable p){
        Long userId = securityUtils.extractUserIdFromAuthContext().orElseThrow(()->new GlobalException(new UserDetailsNotFoundError()));

        return this.machineRepository.findAllByUser(p,userId);
    }

    @HasAccessToResource
    public Machine findById(Long id) {
        return this.machineRepository.findById(id).orElseThrow(()-> new GlobalException(new MachineNotFoundError()));
    }

    @HasAccessToResource
    @Transactional
    public Machine updateMachine(Long id, UpdateMachineDto updatedMachine){


        Machine machine = machineRepository.findById(id)
                .orElseThrow(()-> new GlobalException(new MachineNotFoundError()));


        updateMachineMapper.updateMachineFromDto(updatedMachine, machine);

        if (!Objects.isNull(updatedMachine.getCategories())) {
            machine.setCategories(updatedMachine.getCategories());
        }

        return machineRepository.save(machine);
    }

    @HasAccessToResource
    @Transactional
    public Machine deleteMachine(Long machineId){
        Machine machine = machineRepository.findById(machineId)
                .orElseThrow(()-> new GlobalException(new MachineNotFoundError()));

        machineRepository.deleteById(machineId);
        return machine;
    }

}
