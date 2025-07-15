package com.ptopalidis.cecloud.platform.external.service;

import com.ptopalidis.cecloud.platform.doc.domain.Doc;
import com.ptopalidis.cecloud.platform.doc.repository.DocRepository;
import com.ptopalidis.cecloud.platform.exception.error.DocNotFoundError;
import com.ptopalidis.cecloud.platform.external.domain.MachinePublicFile;
import com.ptopalidis.cecloud.platform.external.domain.MachinePublicFiles;
import com.ptopalidis.cecloud.platform.machinefile.domain.MachineFileSubType;
import com.ptopalidis.cecloud.platform.machinefile.service.MachineFileService;
import com.topcode.pdfgenerator.service.PdfGeneratorService;
import com.topcode.web.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExternalService {

    private final MachineFileService machineFileService;
    private final DocRepository docRepository;
    private final PdfGeneratorService pdfGeneratorService;

    public MachinePublicFiles getMachinePublicFiles(Long machineId) {

        List<MachinePublicFile> generalDescriptions = machineFileService
                .getMachineFilesByMachineAndSubType(machineId, MachineFileSubType.GENERAL_DESCRIPTION)
                .stream()
                .map(machineFile -> MachinePublicFile
                        .builder()
                        .url(machineFile.getUrl())
                        .fileName(machineFile.getOriginalFileName())
                        .subType(machineFile.getSubType().toString())
                        .build())
                .toList();


        return MachinePublicFiles
                .builder()
                .generalDescriptions(generalDescriptions)
                .build();
    }

    public byte[] generateDocPDF(Long snId) throws IOException {

        Doc doc = docRepository.findBySerialNumberId(snId)
                .orElseThrow(()->new GlobalException(new DocNotFoundError()));
        ByteArrayOutputStream outputStream = this.pdfGeneratorService.generatePdf("DOC",doc);

        return outputStream.toByteArray();
    }
}
