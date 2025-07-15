package com.ptopalidis.cecloud.platform.external.controller;

import com.ptopalidis.cecloud.platform.doc.service.DocService;
import com.ptopalidis.cecloud.platform.external.domain.MachinePublicFiles;
import com.ptopalidis.cecloud.platform.external.service.ExternalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ExternalController {

    private final ExternalService externalService;
    private final DocService docService;

    @Operation(summary = "Get machine public files")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MachinePublicFiles.class))})
    @GetMapping("/external/users/{userId}/machines/{machineId}")
    public ResponseEntity<MachinePublicFiles> getMachinePublicFiles(@PathVariable String userId, @PathVariable Long machineId)  {
        return ResponseEntity.status(200).body(this.externalService.getMachinePublicFiles(machineId));
    }

    @Operation(summary = "Check if a doc exists")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Boolean.class))})
    @GetMapping("/external/users/{userId}/machines/{machineId}/serial-numbers/{snId}/doc")
    public ResponseEntity<Boolean> getDocExists(@PathVariable String userId,
                                                @PathVariable Long machineId,
                                                @PathVariable Long snId)  {

        return ResponseEntity.status(200).body(this.docService.docExists(snId));
    }

    @Operation(summary = "Creates a doc pdf")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_PDF_VALUE))
    @GetMapping(value = "/external/users/{userId}/machines/{machineId}/serial-numbers/{snId}/doc/pdf", produces = "application/pdf;charset=UTF-8")
    public ResponseEntity<byte[]> generateDOCPdf(@PathVariable String userId,
                                                 @PathVariable Long machineId,
                                                 @PathVariable Long snId) throws IOException {

        return ResponseEntity.ok().body(this.externalService.generateDocPDF(snId));
    }
}
