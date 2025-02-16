package com.ptopalidis.cecloud.platform.machine.machinefile.service;


import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.ptopalidis.cecloud.platform.common.exception.GlobalException;
import com.ptopalidis.cecloud.platform.common.exception.error.MachineFileNotFoundError;
import com.ptopalidis.cecloud.platform.common.exception.error.MachineFileUploadFailedError;
import com.ptopalidis.cecloud.platform.common.exception.error.MachineNotFoundError;
import com.ptopalidis.cecloud.platform.common.security.annotation.HasAccessToResource;
import com.ptopalidis.cecloud.platform.file.service.S3Service;
import com.ptopalidis.cecloud.platform.machine.domain.Machine;
import com.ptopalidis.cecloud.platform.machine.machinefile.config.MachineFileProperties;
import com.ptopalidis.cecloud.platform.machine.machinefile.domain.MachineFile;
import com.ptopalidis.cecloud.platform.machine.machinefile.domain.MachineFileSubType;
import com.ptopalidis.cecloud.platform.machine.machinefile.repository.MachineFileRepository;
import com.ptopalidis.cecloud.platform.machine.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MachineFileService {

    private final S3Service s3Service;
    private final MachineRepository machineRepository;
    private final MachineFileProperties machineFileProperties;
    private final MachineFileRepository machineFileRepository;

    @Transactional
    @HasAccessToResource
    public MachineFile deleteMachineFile(Long fileId){
        MachineFile file = machineFileRepository.findById(fileId).orElseThrow(() -> new GlobalException(new MachineFileNotFoundError()));
        this.s3Service.deleteFile(file.getS3key());
        machineFileRepository.deleteById(fileId);
        return file;
    }

    @Transactional
    @HasAccessToResource
    public MachineFile uploadMachineFile(Long machineId, MachineFileSubType subType, MultipartFile file)  {
        Machine machine = this.machineRepository.findById(machineId).orElseThrow(()-> new GlobalException(new MachineNotFoundError()));

        try{
            String s3Key = generateS3KeyFromMachine(machine,subType,file);
            this.s3Service.uploadFile(s3Key,file, Optional.of(CannedAccessControlList.PublicRead));

            return machineFileRepository.save(MachineFile.builder()
                    .machine(machine)
                    .originalFileName(file.getOriginalFilename())
                    .url(s3Service.getFileUrl(s3Key))
                    .s3key(s3Key)
                    .size(file.getSize())
                    .subType(subType)
                    .contentType(file.getContentType())
                    .build());
        } catch (IOException exception){
            throw new GlobalException(new MachineFileUploadFailedError());
        }

    }


    private String generateS3KeyFromMachine(Machine machine,MachineFileSubType subType, MultipartFile file){
        return machineFileProperties.getS3directory() +
                "/" +
                machine.getMachineuser().getId() +
                "/machines/" +
                machine.getId() +
                "/" +
                subType.getFileType().toString() +
                "/" +
                subType +
                "/" +
                file.getOriginalFilename();
    }

}
