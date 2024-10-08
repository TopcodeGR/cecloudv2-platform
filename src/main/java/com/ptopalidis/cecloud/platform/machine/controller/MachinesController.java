package com.ptopalidis.cecloud.platform.machine.controller;


import com.ptopalidis.cecloud.platform.common.security.utils.SecurityUtils;
import com.ptopalidis.cecloud.platform.machine.domain.Machine;
import com.ptopalidis.cecloud.platform.machine.domain.dto.UpdateMachineDto;
import com.ptopalidis.cecloud.platform.machine.service.MachinesService;
import com.ptopalidis.cecloud.platform.user.service.UserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MachinesController {


    private final MachinesService machinesService;
    private final UserDetailsService userDetailsService;
    private final SecurityUtils securityUtils;

    @Operation(summary = "Gets machines")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Machine.class))})
    @GetMapping("/machines")
    @PreAuthorize("hasAuthority('GET_MACHINES')")
    public ResponseEntity<Page<Machine>> getMachines(Authentication auth, Pageable p){
        return ResponseEntity.status(200).body(this.machinesService.findAll(p));
    }


    @Operation(summary = "Gets a machine by id")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Machine.class))})
    @GetMapping("/machines/{id}")
    @PreAuthorize("hasAuthority('GET_MACHINE_BY_ID')")
    public ResponseEntity<Machine> getMachineById(@PathVariable Long id){
        return ResponseEntity.status(200).body(this.machinesService.findById(id));
    }


    @Operation(summary = "Update a machine")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Machine.class))})
    @PutMapping("/machines/{id}")
    @PreAuthorize("hasAuthority('GET_MACHINE_BY_ID')")
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
