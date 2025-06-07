package com.ptopalidis.cecloud.platform.admin.controller;

import com.google.zxing.WriterException;
import com.ptopalidis.cecloud.platform.category.domain.MachineCategory;
import com.ptopalidis.cecloud.platform.category.service.MachineCategoriesService;
import com.ptopalidis.cecloud.platform.directive.domain.Directive;
import com.ptopalidis.cecloud.platform.doc.domain.Doc;
import com.ptopalidis.cecloud.platform.doc.service.DocService;
import com.ptopalidis.cecloud.platform.machine.domain.Machine;
import com.ptopalidis.cecloud.platform.machine.domain.MachinesPage;
import com.ptopalidis.cecloud.platform.machine.domain.dto.CreateMachineDto;
import com.ptopalidis.cecloud.platform.machine.domain.dto.UpdateMachineDto;
import com.ptopalidis.cecloud.platform.machine.service.MachinesService;
import com.ptopalidis.cecloud.platform.machinefile.domain.MachineFile;
import com.ptopalidis.cecloud.platform.machinefile.domain.MachineFileSubType;
import com.ptopalidis.cecloud.platform.machinefile.service.MachineFileService;
import com.ptopalidis.cecloud.platform.serialnumber.domain.SerialNumber;
import com.ptopalidis.cecloud.platform.serialnumber.service.SerialNumberService;
import com.ptopalidis.cecloud.platform.directive.service.DirectiveService;
import com.topcode.pdfgenerator.service.PdfGeneratorService;
import com.topcode.web.annotation.IsAdmin;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminMachinesController {


    private final MachinesService machinesService;
    private final MachineCategoriesService machineCategoriesService;
    private final SerialNumberService serialNumberService;
    private final DirectiveService directiveService;
    private final MachineFileService machineFileService;
    private final DocService docService;
    private final PdfGeneratorService pdfGeneratorService;


    @Operation(summary = "Get user machines")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MachinesPage.class))})
    @GetMapping("/admin/users/{userId}/machines")
    @IsAdmin
    public ResponseEntity<MachinesPage> getUserMachines(Pageable p, @PathVariable String userId){
        return ResponseEntity.status(200).body(new MachinesPage(this.machinesService.findAllByUserId(p, userId)));
    }

    @Operation(summary = "Gets a machine by id")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Machine.class))})
    @GetMapping("/admin/users/{userId}/machines/{machineId}")
    @IsAdmin
    public ResponseEntity<Machine> getUserMachine(@PathVariable String userId, @PathVariable Long machineId){
        return ResponseEntity.status(200).body(this.machinesService.findById(machineId));
    }

    @Operation(summary = "Gets machine categories")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,   array = @ArraySchema(schema = @Schema(implementation = MachineCategory.class)))})
    @GetMapping("/admin/machines/categories")
    @IsAdmin
    public ResponseEntity<List<MachineCategory>> getMachineCategories()  {
        return ResponseEntity.status(200).body(this.machineCategoriesService.findAllMachineCategories());
    }

    @Operation(summary = "Create a machine")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    @PostMapping("/admin/users/{userId}/machines")
    @IsAdmin
    public ResponseEntity<Void> createUserMachine(@RequestBody @Valid CreateMachineDto createMachineDto, @PathVariable String userId){
        this.machinesService.createMachine(createMachineDto, userId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update a machine")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Machine.class))})
    @PutMapping("/admin/users/{userId}/machines/{id}")
    @IsAdmin
    public ResponseEntity<Machine> updateMachine(@PathVariable Long id, @RequestBody @Valid UpdateMachineDto updateMachineDto, @PathVariable String userId){
        return ResponseEntity.status(200).body(this.machinesService.updateMachine(id,updateMachineDto));
    }

    @Operation(summary = "Delete a machine")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Machine.class))})
    @DeleteMapping("/admin/users/{userId}/machines/{id}")
    @IsAdmin
    public ResponseEntity<Void> deleteMachine(@PathVariable Long id, @PathVariable String userId){
        this.machinesService.deleteMachine(id);
        return ResponseEntity.status(200).build();
    }

    @Operation(summary = "Batch claims serial numbers")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,  array = @ArraySchema(schema = @Schema(implementation = MachineCategory.class)))})
    @PostMapping("/admin/users/{userId}/machines/{machineId}/serial-numbers/batch")
    @IsAdmin
    public ResponseEntity<List<SerialNumber>> batchClaimSerialNumbers(@PathVariable String userId, @PathVariable Long machineId, @RequestBody Integer serialNumberAmount){
        List<SerialNumber> serialNumbers = this.serialNumberService.batchClaimSerialNumbers(serialNumberAmount, machineId);
        return ResponseEntity.ok().body(serialNumbers);
    }

    @Operation(summary = "Delete a serial number")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,  array = @ArraySchema(schema = @Schema(implementation = MachineCategory.class)))})
    @DeleteMapping("/admin/users/{userId}/machines/{machineId}/serial-numbers/{serialNumberId}")
    @IsAdmin
    public ResponseEntity<Void> deleteSerialNumber(@PathVariable String userId, @PathVariable Long machineId,  @PathVariable Long serialNumberId){
        this.serialNumberService.deleteSerialNumber(serialNumberId);
        return ResponseEntity.status(200).build();
    }

    @Operation(summary = "Create serial number QR code")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.IMAGE_PNG_VALUE)})
    @PostMapping("/admin/users/{userId}/machines/{machineId}/serial-numbers/{serialNumberId}")
    @IsAdmin
    public ResponseEntity<BufferedImage> createSerialNumberQRCode(@PathVariable String userId, @PathVariable Long machineId, @PathVariable Long serialNumberId) throws WriterException {
        BufferedImage qrCode = this.serialNumberService.createSerialNumberQRCode(serialNumberId);
        return ResponseEntity.ok().body(qrCode);
    }

    @Operation(summary = "Gets directives")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,   array = @ArraySchema(schema = @Schema(implementation = Directive.class)))})
    @GetMapping("/admin/directives")
    @IsAdmin
    public ResponseEntity<List<Directive>> getDirectives(){
        return ResponseEntity.status(200).body(directiveService.findAllDirectives());
    }

    @Operation(summary = "Upload a machine file")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MachineFile.class))})
    @PostMapping(path="/admin/users/{userId}/machines/{machineId}/files/{subType}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @IsAdmin
    public ResponseEntity<MachineFile> uploadMachineFile(@PathVariable Long machineId, @PathVariable MachineFileSubType subType, @RequestParam("file") MultipartFile file, @PathVariable String userId){
        return ResponseEntity.status(200).body(this.machineFileService.uploadMachineFile(machineId,subType,file));
    }

    @Operation(summary = "Delete a machine file")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class))})
    @DeleteMapping("/admin/users/{userId}/machines/{machineId}/files/{subType}/{fileId}")
    @IsAdmin
    public ResponseEntity<Void> deleteMachineFile(@PathVariable Long machineId, @PathVariable MachineFileSubType subType, @PathVariable Long fileId, @PathVariable String userId){
        this.machineFileService.deleteMachineFile(fileId);
        return ResponseEntity.status(200).build();
    }

    @Operation(summary = "Creates a doc pdf")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_PDF_VALUE))
    @GetMapping(value = "/admin/users/{userId}/machines/{machineId}/serial-numbers/{serialNumberId}/doc/pdf", produces = "application/pdf;charset=UTF-8")
    @IsAdmin
    public ResponseEntity<byte[]> generateDOCPdf(@PathVariable Long machineId, @PathVariable Long serialNumberId, @PathVariable String userId) throws IOException {

        Doc doc = this.docService.getDocBySerialNumber(serialNumberId);
        ByteArrayOutputStream outputStream = this.pdfGeneratorService.generatePdf("DOC",doc);
        return ResponseEntity.ok().body(outputStream.toByteArray());
    }
}
