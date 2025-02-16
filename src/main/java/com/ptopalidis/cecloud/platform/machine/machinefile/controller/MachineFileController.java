package com.ptopalidis.cecloud.platform.machine.machinefile.controller;

import com.ptopalidis.cecloud.platform.machine.domain.Machine;
import com.ptopalidis.cecloud.platform.machine.machinefile.domain.MachineFile;
import com.ptopalidis.cecloud.platform.machine.machinefile.domain.MachineFileSubType;
import com.ptopalidis.cecloud.platform.machine.machinefile.service.MachineFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class MachineFileController {

    private final MachineFileService machineFileService;

    @Operation(summary = "Upload a machine file")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Machine.class))})
    @PostMapping(path = "/machines/{id}/files/{subType}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    //@PreAuthorize("hasAuthority('GET_MACHINE_BY_ID')")
    public ResponseEntity<MachineFile> uploadMachineFile(@PathVariable Long id, @PathVariable MachineFileSubType subType, @RequestParam("file") MultipartFile file){
        return ResponseEntity.status(200).body(this.machineFileService.uploadMachineFile(id,subType,file));
    }

    @Operation(summary = "Delete a machine file")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Machine.class))})
    @DeleteMapping(path = "/machines/{id}/files/{subType}/{fileId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    //@PreAuthorize("hasAuthority('GET_MACHINE_BY_ID')")
    public ResponseEntity<Void> deleteMachineFile(@PathVariable Long fileId){
        this.machineFileService.deleteMachineFile(fileId);
        return ResponseEntity.status(200).build();
    }

}
