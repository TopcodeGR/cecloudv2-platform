package com.ptopalidis.cecloud.platform.machine.pcd.controller;

import com.ptopalidis.cecloud.platform.machine.category.domain.MachineCategory;
import com.ptopalidis.cecloud.platform.machine.pcd.domain.Pcd;
import com.ptopalidis.cecloud.platform.machine.pcd.domain.dto.CreatePcdDto;
import com.ptopalidis.cecloud.platform.machine.pcd.service.PcdService;
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
public class PcdController {

    private final PcdService pcdService;

    @Operation(summary = "Creates a pcd")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MachineCategory.class)))
    @PostMapping(value = "/machines/{machineId}/serial-numbers/{snId}/pcd", consumes = {MediaType.APPLICATION_JSON_VALUE})
    //@PreAuthorize("hasAuthority('CREATE_MACHINE_CATEGORY')")
    public ResponseEntity<Pcd> createPcd(@PathVariable Long snId, @RequestBody @Valid CreatePcdDto createPcdDto){
        return ResponseEntity.ok(this.pcdService.createPcd(snId, createPcdDto));
    }
}
