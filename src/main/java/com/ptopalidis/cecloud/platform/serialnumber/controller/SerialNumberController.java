package com.ptopalidis.cecloud.platform.serialnumber.controller;

import com.ptopalidis.cecloud.platform.serialnumber.domain.SerialNumber;
import com.ptopalidis.cecloud.platform.serialnumber.domain.dto.UpdateSerialNumberDto;
import com.ptopalidis.cecloud.platform.serialnumber.service.SerialNumberService;
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

@RestController
@RequiredArgsConstructor
public class SerialNumberController {

    private final SerialNumberService serialNumberService;


    @Operation(summary = "Gets a serial number by id")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SerialNumber.class))})
    @GetMapping("/serial-numbers/{id}")
    @RequiredAuthorities(authorities = {"GET_SERIAL_NUMBER"})
    public ResponseEntity<SerialNumber> getSerialNumberById(@PathVariable Long id){
        return ResponseEntity.status(200).body(this.serialNumberService.getSerialNumberById(id));
    }



    @Operation(summary = "Updates a serial number")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SerialNumber.class)))
    @PutMapping(value = "/machines/{machineId}/serial-numbers/{snId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @RequiredAuthorities(authorities = {"UPDATE_SERIAL_NUMBER"})
    public ResponseEntity<SerialNumber> updateSerialNumber(@PathVariable Long machineId, @PathVariable Long snId, @RequestBody @Valid UpdateSerialNumberDto updateSerialNumberDto){
        return ResponseEntity.ok(this.serialNumberService.updateSerialNumber(snId, updateSerialNumberDto));
    }
}
