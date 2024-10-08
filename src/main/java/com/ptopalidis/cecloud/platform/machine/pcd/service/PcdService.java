package com.ptopalidis.cecloud.platform.machine.pcd.service;


import com.ptopalidis.cecloud.platform.common.exception.GlobalException;
import com.ptopalidis.cecloud.platform.common.exception.error.SerialNumberNotFoundError;
import com.ptopalidis.cecloud.platform.common.security.annotation.HasAccessToResource;
import com.ptopalidis.cecloud.platform.machine.pcd.domain.Pcd;
import com.ptopalidis.cecloud.platform.machine.pcd.domain.dto.CreatePcdDto;
import com.ptopalidis.cecloud.platform.machine.pcd.repository.PcdRepository;
import com.ptopalidis.cecloud.platform.machine.pcd.transform.CreatePcdMapper;
import com.ptopalidis.cecloud.platform.machine.serialnumber.domain.SerialNumber;
import com.ptopalidis.cecloud.platform.machine.serialnumber.repository.SerialNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PcdService {


    private final PcdRepository pcdRepository;
    private final SerialNumberRepository serialNumberRepository;
    private final CreatePcdMapper createPcdMapper;


    @Transactional
    @HasAccessToResource
    public Pcd createPcd(Long serialNumberId, CreatePcdDto createPcdDto){
        SerialNumber serialNumber = serialNumberRepository.findById(serialNumberId).orElseThrow(()->new GlobalException(new SerialNumberNotFoundError()));

        Pcd pcd = createPcdMapper.toEntity(createPcdDto);
        pcd.setSerialNumber(serialNumber);

        return pcdRepository.save(pcd);
    }
}
