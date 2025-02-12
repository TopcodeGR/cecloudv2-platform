package com.ptopalidis.cecloud.platform.machine.service;

import com.ptopalidis.cecloud.platform.account.domain.Account;
import com.ptopalidis.cecloud.platform.common.exception.GlobalException;
import com.ptopalidis.cecloud.platform.common.exception.error.AccountNotFoundError;
import com.ptopalidis.cecloud.platform.common.exception.error.MachineNotFoundError;
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
        Account account = securityUtils.extractAccountFromAuthContext().orElseThrow(()->new GlobalException(new AccountNotFoundError()));
        return this.machineRepository.findAllByAccount(p,account.getId());
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
