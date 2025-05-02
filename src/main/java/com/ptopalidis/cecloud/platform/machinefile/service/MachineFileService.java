package com.ptopalidis.cecloud.platform.machinefile.service;


import com.ptopalidis.cecloud.platform.exception.error.MachineFileNotFoundError;
import com.ptopalidis.cecloud.platform.exception.error.MachineFileUploadFailedError;
import com.ptopalidis.cecloud.platform.exception.error.MachineNotFoundError;
import com.ptopalidis.cecloud.platform.machine.domain.Machine;
import com.ptopalidis.cecloud.platform.machinefile.config.MachineFileProperties;
import com.ptopalidis.cecloud.platform.machinefile.domain.MachineFile;
import com.ptopalidis.cecloud.platform.machinefile.domain.MachineFileSubType;
import com.ptopalidis.cecloud.platform.machinefile.repository.MachineFileRepository;
import com.ptopalidis.cecloud.platform.machine.repository.MachineRepository;
import com.topcode.files.service.S3Service;
import com.topcode.web.annotation.HasAccessToResource;
import com.topcode.web.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;

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
            PutObjectResponse uploadFileResponse =
                    this.s3Service.uploadFile(s3Key,file, ObjectCannedACL.PUBLIC_READ);

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
                machine.getAccountId() +
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
