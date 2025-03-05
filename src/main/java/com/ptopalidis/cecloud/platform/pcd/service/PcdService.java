package com.ptopalidis.cecloud.platform.pcd.service;


import com.ptopalidis.cecloud.platform.exception.error.SerialNumberNotFoundError;
import com.ptopalidis.cecloud.platform.pcd.domain.Pcd;
import com.ptopalidis.cecloud.platform.pcd.domain.dto.CreatePcdDto;
import com.ptopalidis.cecloud.platform.pcd.repository.PcdRepository;
import com.ptopalidis.cecloud.platform.pcd.transform.CreatePcdMapper;
import com.ptopalidis.cecloud.platform.serialnumber.domain.SerialNumber;
import com.ptopalidis.cecloud.platform.serialnumber.repository.SerialNumberRepository;
import com.topcode.web.annotation.HasAccessToResource;
import com.topcode.web.exception.GlobalException;
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
