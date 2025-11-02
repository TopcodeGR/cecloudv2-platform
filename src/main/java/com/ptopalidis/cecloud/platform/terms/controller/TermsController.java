package com.ptopalidis.cecloud.platform.terms.controller;

import com.ptopalidis.cecloud.platform.terms.service.TermsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TermsController {


    private final TermsService termsService;

    @Operation(summary = "Gets terms")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.TEXT_PLAIN_VALUE,schema = @Schema(implementation = String.class))})
    @GetMapping("/terms")
    public ResponseEntity<String> getTerms(){
        return ResponseEntity.status(200).body(termsService.getTerms());
    }

    @Operation(summary = "Accept terms")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = Void.class))})
    @PostMapping("/terms/accept")
    public ResponseEntity<Void> acceptTerms(){
        termsService.acceptTerms();
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Reject terms")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = Void.class))})
    @PostMapping("/terms/reject")
    public ResponseEntity<Void> rejectTerms(){
        termsService.rejectTerms();
        return ResponseEntity.ok().build();
    }

}
