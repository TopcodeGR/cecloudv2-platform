package com.ptopalidis.cecloud.platform.machine.serialnumber.controller;

import com.ptopalidis.cecloud.platform.machine.category.domain.MachineCategory;
import com.ptopalidis.cecloud.platform.machine.serialnumber.domain.SerialNumber;
import com.ptopalidis.cecloud.platform.machine.serialnumber.domain.dto.CreateSerialNumberDto;
import com.ptopalidis.cecloud.platform.machine.serialnumber.service.SerialNumberService;
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
public class SerialNumberController {

    private final SerialNumberService serialNumberService;

    @Operation(summary = "Creates a serial number")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MachineCategory.class)))
    @PostMapping(value = "/machines/{machineId}/serial-numbers", consumes = {MediaType.APPLICATION_JSON_VALUE})
    //@PreAuthorize("hasAuthority('CREATE_MACHINE_CATEGORY')")
    public ResponseEntity<SerialNumber> createSerialNumber(@PathVariable Long machineId, @RequestBody @Valid CreateSerialNumberDto createSerialNumberDto){
        return ResponseEntity.ok(this.serialNumberService.createSerialNumber(createSerialNumberDto, machineId));
    }
}
