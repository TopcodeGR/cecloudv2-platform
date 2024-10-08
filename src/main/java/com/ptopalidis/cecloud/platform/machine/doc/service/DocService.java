package com.ptopalidis.cecloud.platform.machine.doc.service;

import com.ptopalidis.cecloud.platform.common.exception.GlobalException;
import com.ptopalidis.cecloud.platform.common.exception.error.SerialNumberNotFoundError;
import com.ptopalidis.cecloud.platform.common.security.annotation.HasAccessToResource;
import com.ptopalidis.cecloud.platform.machine.doc.domain.Doc;
import com.ptopalidis.cecloud.platform.machine.doc.domain.dto.CreateDocDto;
import com.ptopalidis.cecloud.platform.machine.doc.repository.DocRepository;
import com.ptopalidis.cecloud.platform.machine.doc.transform.CreateDocMapper;
import com.ptopalidis.cecloud.platform.machine.serialnumber.domain.SerialNumber;
import com.ptopalidis.cecloud.platform.machine.serialnumber.repository.SerialNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DocService {

    private final DocRepository docRepository;
    private final SerialNumberRepository serialNumberRepository;
    private final CreateDocMapper createDocMapper;


    @Transactional
    @HasAccessToResource
    public Doc createDoc(Long serialNumberId, CreateDocDto createDocDto){
        SerialNumber serialNumber = serialNumberRepository.findById(serialNumberId).orElseThrow(()->new GlobalException(new SerialNumberNotFoundError()));

        Doc doc = createDocMapper.toEntity(createDocDto);
        doc.setSerialNumber(serialNumber);

        return docRepository.save(doc);
    }
}
