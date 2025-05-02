package com.ptopalidis.cecloud.platform.category.contorller;

import com.ptopalidis.cecloud.platform.category.domain.MachineCategory;
import com.ptopalidis.cecloud.platform.category.domain.dto.CreateMachineCategoryDto;
import com.ptopalidis.cecloud.platform.category.service.MachineCategoriesService;
import com.topcode.web.annotation.RequiredAuthorities;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MachineCategoriesController {

    private final MachineCategoriesService machineCategoriesService;

    @Operation(summary = "Gets machine categories")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,   array = @ArraySchema(schema = @Schema(implementation = MachineCategory.class)))})
    @GetMapping("/machines/categories")
    @RequiredAuthorities(authorities = {"GET_MACHINE_CATEGORIES"})
    public ResponseEntity<List<MachineCategory>> getMachineCategories()  {
        return ResponseEntity.status(200).body(this.machineCategoriesService.findAllMachineCategories());
    }

    @Operation(summary = "Deletes machine category")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Boolean.class)))
    @DeleteMapping("/machines/categories/{id}")
    public ResponseEntity<Boolean> deleteMachineCategory(@PathVariable Long id){
        return ResponseEntity.ok(this.machineCategoriesService.deleteMachineCategoryById(id));
    }

    @Operation(summary = "Creates machine category")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MachineCategory.class)))
    @PostMapping("/machines/categories")
    public ResponseEntity<MachineCategory> createMachineCategory(@RequestBody @Valid CreateMachineCategoryDto machineCategoryDto){
        return ResponseEntity.ok(this.machineCategoriesService.saveMachineCategory(machineCategoryDto));
    }
}
