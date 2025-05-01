package com.ptopalidis.cecloud.platform.doc.service;

import com.ptopalidis.cecloud.platform.exception.error.DocNotFoundError;
import com.ptopalidis.cecloud.platform.exception.error.SerialNumberNotFoundError;
import com.ptopalidis.cecloud.platform.doc.domain.Doc;
import com.ptopalidis.cecloud.platform.doc.domain.dto.CreateDocDto;
import com.ptopalidis.cecloud.platform.doc.repository.DocRepository;
import com.ptopalidis.cecloud.platform.doc.transform.CreateDocMapper;
import com.ptopalidis.cecloud.platform.serialnumber.domain.SerialNumber;
import com.ptopalidis.cecloud.platform.serialnumber.repository.SerialNumberRepository;
import com.topcode.web.annotation.HasAccessToResource;
import com.topcode.web.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class DocService {

    private final DocRepository docRepository;
    private final SerialNumberRepository serialNumberRepository;
    private final CreateDocMapper createDocMapper;


    @HasAccessToResource
    public Doc getDocBySerialNumber(Long serialNumberId) {
        return docRepository.findBySerialNumberId(serialNumberId)
                .orElseThrow(()->new GlobalException(new DocNotFoundError()));
    }


    @Transactional
    @HasAccessToResource
    public Doc createDoc(Long serialNumberId, CreateDocDto createDocDto){
        SerialNumber serialNumber = serialNumberRepository.findById(serialNumberId).orElseThrow(()->new GlobalException(new SerialNumberNotFoundError()));


        Doc doc = Doc
                .builder()
                .serialNumber(serialNumber)
                .issuanceDate(createDocDto.getIssuanceDate())
                .productionDate(createDocDto.getProductionDate())
                .productionManager(createDocDto.getProductionManager())
                .build();
        //Doc doc = createDocMapper.toEntity(createDocDto);
       // doc.setSerialNumber(serialNumber);

        return docRepository.save(doc);
    }

    @Transactional
    @HasAccessToResource
    public Doc updateDoc(Long serialNumberId, CreateDocDto createDocDto){
        SerialNumber serialNumber = serialNumberRepository.findById(serialNumberId).orElseThrow(()->new GlobalException(new SerialNumberNotFoundError()));

        Doc doc = serialNumber.getDoc();


        if (ObjectUtils.isEmpty(doc)) {
            throw  new GlobalException(new DocNotFoundError());
        }
        doc.setProductionDate(createDocDto.getProductionDate());
        doc.setProductionManager(createDocDto.getProductionManager());
        doc.setIssuanceDate(createDocDto.getIssuanceDate());

        return docRepository.save(doc);
    }
}
