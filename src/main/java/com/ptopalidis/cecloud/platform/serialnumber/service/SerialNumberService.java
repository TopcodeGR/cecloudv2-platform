package com.ptopalidis.cecloud.platform.serialnumber.service;


import com.ptopalidis.cecloud.platform.exception.error.MachineNotFoundError;
import com.ptopalidis.cecloud.platform.machine.domain.Machine;
import com.ptopalidis.cecloud.platform.machine.repository.MachineRepository;
import com.ptopalidis.cecloud.platform.serialnumber.domain.SerialNumber;
import com.ptopalidis.cecloud.platform.serialnumber.domain.dto.CreateSerialNumberDto;
import com.ptopalidis.cecloud.platform.serialnumber.repository.SerialNumberRepository;
import com.ptopalidis.cecloud.platform.serialnumber.transform.CreateSerialNumberMapper;
import com.topcode.web.annotation.HasAccessToResource;
import com.topcode.web.exception.GlobalException;
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
