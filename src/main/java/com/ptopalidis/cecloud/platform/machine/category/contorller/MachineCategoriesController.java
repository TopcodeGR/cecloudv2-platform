package com.ptopalidis.cecloud.platform.machine.category.contorller;

import com.ptopalidis.cecloud.platform.machine.category.domain.MachineCategory;
import com.ptopalidis.cecloud.platform.machine.category.domain.dto.CreateMachineCategoryDto;
import com.ptopalidis.cecloud.platform.machine.category.service.MachineCategoriesService;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MachineCategoriesController {

    private final MachineCategoriesService machineCategoriesService;

    @Operation(summary = "Gets machine categories")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MachineCategory.class)))
    @GetMapping("/machines/categories")
    @PreAuthorize("hasAuthority('GET_MACHINE_CATEGORIES')")
    public ResponseEntity<Page<MachineCategory>> getMachineCategories(Pageable p)  {
        return ResponseEntity.status(200).body(this.machineCategoriesService.findAllMachineCategories(p));
    }

    @Operation(summary = "Deletes machine category")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Boolean.class)))
    @DeleteMapping("/machines/categories/{id}")
    @PreAuthorize("hasAuthority('DELETE_MACHINE_CATEGORY')")
    public ResponseEntity<Boolean> deleteMachineCategory(@PathVariable Long id){
        return ResponseEntity.ok(this.machineCategoriesService.deleteMachineCategoryById(id));
    }

    @Operation(summary = "Creates machine category")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MachineCategory.class)))
    @PostMapping("/machines/categories")
    @PreAuthorize("hasAuthority('CREATE_MACHINE_CATEGORY')")
    public ResponseEntity<MachineCategory> createMachineCategory(@RequestBody @Valid CreateMachineCategoryDto machineCategoryDto){
        return ResponseEntity.ok(this.machineCategoriesService.saveMachineCategory(machineCategoryDto));
    }
}
