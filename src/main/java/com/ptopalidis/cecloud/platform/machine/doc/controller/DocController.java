package com.ptopalidis.cecloud.platform.machine.doc.controller;

import com.ptopalidis.cecloud.platform.machine.category.domain.MachineCategory;
import com.ptopalidis.cecloud.platform.machine.doc.service.DocService;
import com.ptopalidis.cecloud.platform.machine.doc.domain.Doc;
import com.ptopalidis.cecloud.platform.machine.doc.domain.dto.CreateDocDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DocController {

    private final DocService docService;

    @Operation(summary = "Creates a doc")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MachineCategory.class)))
    @PostMapping(value = "/machines/{machineId}/serial-numbers/{snId}/doc", consumes = {MediaType.APPLICATION_JSON_VALUE})
    //@PreAuthorize("hasAuthority('CREATE_MACHINE_CATEGORY')")
    public ResponseEntity<Doc> createDoc(@PathVariable Long snId, @RequestBody @Valid CreateDocDto createDocDto){
        return ResponseEntity.ok(this.docService.createDoc(snId, createDocDto));
    }
}
