package com.ptopalidis.cecloud.platform.machine.materialslist.controller;

import com.ptopalidis.cecloud.platform.machine.category.domain.MachineCategory;
import com.ptopalidis.cecloud.platform.machine.materialslist.domain.MaterialsList;
import com.ptopalidis.cecloud.platform.machine.materialslist.domain.dto.CreateMaterialListDto;
import com.ptopalidis.cecloud.platform.machine.materialslist.domain.dto.UpdateMaterialListDto;
import com.ptopalidis.cecloud.platform.machine.materialslist.service.MaterialsListService;
import com.ptopalidis.cecloud.platform.pdfgenerator.service.PdfGeneratorService;
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
public class MaterialsListController {

    private final MaterialsListService materialsListService;
    private final PdfGeneratorService pdfGeneratorService;

    @Operation(summary = "Gets the material list of a serial number")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MachineCategory.class)))
    @GetMapping(value = "/machines/{machineId}/serial-numbers/{snId}/materials-list")
    //@PreAuthorize("hasAuthority('CREATE_MACHINE_CATEGORY')")
    public ResponseEntity<MaterialsList> getMaterialsList(@PathVariable Long machineId, @PathVariable Long snId){
        return ResponseEntity.ok(this.materialsListService.getMaterialsListBySerialNumber(snId));
    }

    @Operation(summary = "Creates a materials list")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MachineCategory.class)))
    @PostMapping(value = "/machines/{machineId}/serial-numbers/{snId}/materials-list", consumes = {MediaType.APPLICATION_JSON_VALUE})
    //@PreAuthorize("hasAuthority('CREATE_MACHINE_CATEGORY')")
    public ResponseEntity<MaterialsList> createMaterialsList(@PathVariable Long machineId, @PathVariable Long snId, @RequestBody @Valid CreateMaterialListDto createMaterialListDto){
        return ResponseEntity.ok(this.materialsListService.createMaterialsList(snId, createMaterialListDto));
    }

    @Operation(summary = "Updates a materials list")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MachineCategory.class)))
    @PutMapping(value = "/machines/{machineId}/serial-numbers/{snId}/materials-list", consumes = {MediaType.APPLICATION_JSON_VALUE})
    //@PreAuthorize("hasAuthority('CREATE_MACHINE_CATEGORY')")
    public ResponseEntity<MaterialsList> updateMaterialsList(@PathVariable Long machineId, @PathVariable Long snId, @RequestBody @Valid UpdateMaterialListDto updateMaterialListDto){
        return ResponseEntity.ok(this.materialsListService.updateMaterialsList(snId, updateMaterialListDto));
    }

    @Operation(summary = "Creates a materials list pdf")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_PDF_VALUE,  schema = @Schema(implementation = MachineCategory.class)))
    @GetMapping(value = "/machines/{machineId}/serial-numbers/{snId}/materials-list/pdf", produces = "application/pdf;charset=UTF-8")
    //@PreAuthorize("hasAuthority('CREATE_MACHINE_CATEGORY')")
    public ResponseEntity<byte[]> generateMaterialsListPdf(@PathVariable Long machineId, @PathVariable Long snId) throws IOException {

        MaterialsList materialsList = this.materialsListService.getMaterialsListBySerialNumber(snId);
        ByteArrayOutputStream outputStream = this.pdfGeneratorService.generatePdf("MATERIALS_LIST",materialsList);

        return ResponseEntity.ok().headers(this.pdfGeneratorService.getPdfHttpHeaders("materials-list.pdf")).body(outputStream.toByteArray());
    }
}
