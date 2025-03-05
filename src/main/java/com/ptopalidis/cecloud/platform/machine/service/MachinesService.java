package com.ptopalidis.cecloud.platform.machine.service;


import com.ptopalidis.cecloud.platform.exception.error.AccountNotFoundError;
import com.ptopalidis.cecloud.platform.exception.error.MachineNotFoundError;
import com.ptopalidis.cecloud.platform.machine.domain.Machine;
import com.ptopalidis.cecloud.platform.machine.domain.dto.UpdateMachineDto;
import com.ptopalidis.cecloud.platform.machine.repository.MachineRepository;
import com.ptopalidis.cecloud.platform.machine.transform.UpdateMachineMapper;
import com.topcode.web.annotation.HasAccessToResource;
import com.topcode.web.domain.Account;
import com.topcode.web.exception.GlobalException;
import com.topcode.web.service.SecurityUtilsService;
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
    private final SecurityUtilsService securityUtilsService;

    public Page<Machine> findAll(Pageable p){
        Account account = securityUtilsService.extractAccountFromAuthContext().orElseThrow(()->new GlobalException(new AccountNotFoundError()));
        return this.machineRepository.findAllByAccount(p, account.getId());
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
