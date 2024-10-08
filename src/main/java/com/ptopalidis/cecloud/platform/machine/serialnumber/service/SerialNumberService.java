package com.ptopalidis.cecloud.platform.machine.serialnumber.service;


import com.ptopalidis.cecloud.platform.common.exception.GlobalException;
import com.ptopalidis.cecloud.platform.common.exception.error.MachineNotFoundError;
import com.ptopalidis.cecloud.platform.common.security.annotation.HasAccessToResource;
import com.ptopalidis.cecloud.platform.machine.domain.Machine;
import com.ptopalidis.cecloud.platform.machine.repository.MachineRepository;
import com.ptopalidis.cecloud.platform.machine.serialnumber.domain.SerialNumber;
import com.ptopalidis.cecloud.platform.machine.serialnumber.domain.dto.CreateSerialNumberDto;
import com.ptopalidis.cecloud.platform.machine.serialnumber.repository.SerialNumberRepository;
import com.ptopalidis.cecloud.platform.machine.serialnumber.transform.CreateSerialNumberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SerialNumberService {

    private final SerialNumberRepository serialNumberRepository;
    private final MachineRepository machineRepository;
    private final CreateSerialNumberMapper createSerialNumberMapper;

    @Transactional
    @HasAccessToResource
    public SerialNumber createSerialNumber(CreateSerialNumberDto createSerialNumberDto,Long machineId) {

        Machine machine = this.machineRepository.findById(machineId).orElseThrow(()-> new GlobalException(new MachineNotFoundError()));
        SerialNumber serialNumber = createSerialNumberMapper.toEntity(createSerialNumberDto);
        serialNumber.setMachine(machine);
        return serialNumberRepository.save(serialNumber);
    }
}
