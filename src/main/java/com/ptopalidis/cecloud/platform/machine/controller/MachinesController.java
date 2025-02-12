package com.ptopalidis.cecloud.platform.machine.controller;


import com.ptopalidis.cecloud.platform.common.security.annotation.RequiredAuthorities;
import com.ptopalidis.cecloud.platform.common.security.domain.AuthorityCode;
import com.ptopalidis.cecloud.platform.machine.domain.Machine;
import com.ptopalidis.cecloud.platform.machine.domain.MachinesPage;
import com.ptopalidis.cecloud.platform.machine.domain.dto.UpdateMachineDto;
import com.ptopalidis.cecloud.platform.machine.service.MachinesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MachinesController {

    private final MachinesService machinesService;

    @Operation(summary = "Gets machines")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MachinesPage.class))})
    @GetMapping("/machines")
    @RequiredAuthorities(authorities = {AuthorityCode.GET_MACHINES})
    public ResponseEntity<MachinesPage> getMachines(Pageable p){
        return ResponseEntity.status(200).body(new MachinesPage(this.machinesService.findAll(p)));
    }


    @Operation(summary = "Gets a machine by id")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Machine.class))})
    @GetMapping("/machines/{id}")
    @RequiredAuthorities(authorities = {AuthorityCode.GET_MACHINE})
    public ResponseEntity<Machine> getMachineById(@PathVariable Long id){
        return ResponseEntity.status(200).body(this.machinesService.findById(id));
    }


    @Operation(summary = "Update a machine")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Machine.class))})
    @PutMapping("/machines/{id}")
    @RequiredAuthorities(authorities = {AuthorityCode.UPDATE_MACHINE})
    public ResponseEntity<Machine> updateMachine(@PathVariable Long id, @RequestBody @Valid UpdateMachineDto updateMachineDto){
        return ResponseEntity.status(200).body(this.machinesService.updateMachine(id,updateMachineDto));
    }

    @Operation(summary = "Delete a machine")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Machine.class))})
    @DeleteMapping("/machines/{id}")
   // @PreAuthorize("hasAuthority('GET_MACHINE_BY_ID')")
    public ResponseEntity<Void> deleteMachine(@PathVariable Long id){
        this.machinesService.deleteMachine(id);
        return ResponseEntity.status(200).build();
    }
}
