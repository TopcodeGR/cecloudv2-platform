package com.ptopalidis.cecloud.platform.doc.controller;

import com.ptopalidis.cecloud.platform.doc.domain.Doc;
import com.ptopalidis.cecloud.platform.doc.domain.dto.CreateDocDto;
import com.ptopalidis.cecloud.platform.doc.service.DocService;
import com.topcode.pdfgenerator.service.PdfGeneratorService;
import com.topcode.web.annotation.RequiredAuthorities;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class DocController {

    private final DocService docService;
    private final PdfGeneratorService pdfGeneratorService;

    @Operation(summary = "Creates a doc")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Doc.class)))
    @PostMapping(value = "/machines/{machineId}/serial-numbers/{snId}/doc", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @RequiredAuthorities(authorities = {"CREATE_DOC"})
    public ResponseEntity<Doc> createDoc(@PathVariable Long machineId, @PathVariable Long snId, @RequestBody @Valid CreateDocDto createDocDto){
        return ResponseEntity.ok(this.docService.createDoc(snId, createDocDto));
    }

    @Operation(summary = "Updates a doc")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Doc.class)))
    @PutMapping(value = "/machines/{machineId}/serial-numbers/{snId}/doc", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @RequiredAuthorities(authorities = {"UPDATE_DOC"})
    public ResponseEntity<Doc> updateDoc(@PathVariable Long machineId, @PathVariable Long snId, @RequestBody @Valid CreateDocDto createDocDto){
        return ResponseEntity.ok(this.docService.updateDoc(snId, createDocDto));
    }


    @Operation(summary = "Creates a doc pdf")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_PDF_VALUE))
    @GetMapping(value = "/machines/{machineId}/serial-numbers/{snId}/doc/pdf", produces = "application/pdf;charset=UTF-8")
    @RequiredAuthorities(authorities = {"GENERATE_DOC_PDF"})
    public ResponseEntity<byte[]> generateDOCPdf(@PathVariable Long machineId, @PathVariable Long snId) throws IOException {

        Doc doc = this.docService.getDocBySerialNumber(snId);
        ByteArrayOutputStream outputStream = this.pdfGeneratorService.generatePdf(
                doc
                .getSerialNumber()
                        .getMachine()
                        .getDirective().getDocFileTemplate().getName(),
                doc);
        return ResponseEntity.ok().body(outputStream.toByteArray());
    }
}
