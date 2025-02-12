package com.ptopalidis.cecloud.platform.ui.controller;


import com.ptopalidis.cecloud.platform.ui.domain.Functionality;
import com.ptopalidis.cecloud.platform.ui.service.UIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UIController {

    private final UIService uiService;

    @Operation(summary = "Gets ui functionality")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,   array = @ArraySchema(schema = @Schema(implementation = Functionality.class)))})
    @GetMapping("/ui/functionality")
    public ResponseEntity<List<Functionality>> getFunctionality(){
        return ResponseEntity.ok().body(this.uiService.getFunctionality());
    }

}
