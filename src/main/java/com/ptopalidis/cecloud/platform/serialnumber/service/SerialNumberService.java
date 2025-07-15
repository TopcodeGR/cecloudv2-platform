package com.ptopalidis.cecloud.platform.serialnumber.service;


import com.google.zxing.WriterException;
import com.ptopalidis.cecloud.platform.exception.error.MachineNotFoundError;
import com.ptopalidis.cecloud.platform.exception.error.SerialNumberNotFoundError;
import com.ptopalidis.cecloud.platform.machine.domain.Machine;
import com.ptopalidis.cecloud.platform.machine.repository.MachineRepository;
import com.ptopalidis.cecloud.platform.serialnumber.domain.SerialNumber;
import com.ptopalidis.cecloud.platform.serialnumber.domain.dto.CreateSerialNumberDto;
import com.ptopalidis.cecloud.platform.serialnumber.domain.dto.UpdateSerialNumberDto;
import com.ptopalidis.cecloud.platform.serialnumber.repository.SerialNumberRepository;
import com.ptopalidis.cecloud.platform.serialnumber.transform.CreateSerialNumberMapper;
import com.topcode.pdfgenerator.service.QRCodeGeneratorService;
import com.topcode.web.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SerialNumberService {

    private final SerialNumberRepository serialNumberRepository;
    private final MachineRepository machineRepository;
    private final CreateSerialNumberMapper createSerialNumberMapper;
    private final QRCodeGeneratorService qrCodeGeneratorService;


    @Value( "${deployment-url}" )
    private String deploymentUrl;


    public SerialNumber updateSerialNumber(Long serialNumberId, UpdateSerialNumberDto updateSerialNumberDto) {

        SerialNumber serialNumber =   serialNumberRepository.findById(serialNumberId)
                .orElseThrow(()-> new GlobalException(new SerialNumberNotFoundError()));


        serialNumber.setSn(updateSerialNumberDto.getSn());


        return serialNumberRepository.save(serialNumber);
    }

    public SerialNumber getSerialNumberById(Long serialNumberId) {
        return serialNumberRepository.findById(serialNumberId)
                .orElseThrow(()-> new GlobalException(new SerialNumberNotFoundError()));
    }

    @Transactional
    public List<SerialNumber> batchClaimSerialNumbers(Integer numberOfSerialNumbers, Long machineId){

        List<SerialNumber> serialNumbers = new ArrayList<>();
        Machine machine = this.machineRepository.findById(machineId).orElseThrow(()-> new GlobalException(new MachineNotFoundError()));

        for (int i=0; i<numberOfSerialNumbers; i++){
            CreateSerialNumberDto serialNumberDto = CreateSerialNumberDto.builder().sn(UUID.randomUUID().toString()).build();
            SerialNumber serialNumber = createSerialNumberMapper.toEntity(serialNumberDto);
            serialNumber.setMachine(machine);
            SerialNumber savedSerialNumber = this.serialNumberRepository.save(serialNumber);
            savedSerialNumber.setSn(String.valueOf(savedSerialNumber.getId()));
            savedSerialNumber = this.serialNumberRepository.save(serialNumber);
            serialNumbers.add(savedSerialNumber);
        }

        return serialNumbers;
    }

    @Transactional
    public SerialNumber deleteSerialNumber(Long id) {
        SerialNumber serialNumber = serialNumberRepository.findById(id)
                .orElseThrow(()-> new GlobalException(new SerialNumberNotFoundError()));

        serialNumberRepository.deleteById(id);
        return serialNumber;
    }



    public BufferedImage createSerialNumberQRCode(Long id) throws WriterException {
        SerialNumber serialNumber = serialNumberRepository.findById(id)
                .orElseThrow(()-> new GlobalException(new SerialNumberNotFoundError()));

        return qrCodeGeneratorService.generateQRCode(deploymentUrl +
                "/public-ui" +
                "/users" +
                "/" + serialNumber.getMachine().getAccount().getUserid() +
                "/machines" +
                "/" + serialNumber.getMachine().getId() +
                "/serial-numbers/" + serialNumber.getId());

    }

}
