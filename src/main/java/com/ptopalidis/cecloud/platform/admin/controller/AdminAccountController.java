package com.ptopalidis.cecloud.platform.admin.controller;


import com.ptopalidis.cecloud.platform.account.domain.CECloudv2Account;
import com.ptopalidis.cecloud.platform.account.domain.CECloudv2AccountUpdateDTO;
import com.ptopalidis.cecloud.platform.account.service.CECloudv2AccountService;
import com.topcode.web.annotation.IsAdmin;
import com.topcode.web.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AdminAccountController {

    private final AccountService<CECloudv2Account> accountService;
    private final CECloudv2AccountService ceCloudv2AccountService;

    @Operation(summary = "Get user account")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CECloudv2Account.class))})
    @GetMapping("/admin/users/{userId}/account")
    @IsAdmin
    public ResponseEntity<CECloudv2Account> getUserAccount(@PathVariable String userId)  {
        return ResponseEntity.status(200).body(this.accountService.findByUserId(userId));
    }

    @Operation(summary = "Create user account")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation =  CECloudv2Account.class))})
    @PostMapping("/admin/users/{userId}/account")
    @IsAdmin
    public ResponseEntity<CECloudv2Account> createUserAccount(@RequestBody CECloudv2Account account, @PathVariable String userId)  {
        return ResponseEntity.status(200).body(this.accountService.saveAccount(account, userId));
    }

    @Operation(summary = "Update user account")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation =  CECloudv2Account.class))})
    @PutMapping(path="/admin/users/{userId}/account", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @IsAdmin
    public ResponseEntity<CECloudv2Account> updateUserAccount(@PathVariable String userId,
                                                              @RequestPart("account") CECloudv2AccountUpdateDTO accountUpdateDTO,
                                                              @RequestPart(value="logo", required=false) MultipartFile logo,
                                                              @RequestPart(value="signature", required = false) MultipartFile signature) throws IOException {
        return ResponseEntity.status(200).body(this.ceCloudv2AccountService.updateAccount(accountUpdateDTO,logo,signature,userId));
    }

    @Operation(summary = "Gets an account's authorities")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,   schema = @Schema(type = "object", implementation = HashMap.class))})
    @GetMapping("/admin/accounts/{userId}/authorities")
    @IsAdmin
    public ResponseEntity<Map<String, Boolean>> getAccountAuthorities(@PathVariable String userId){
        return ResponseEntity.ok().body(this.accountService.getAccountAuthorities(userId));
    }


    @Operation(summary = "Edit an authority of an account")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    @PutMapping("/admin/accounts/{userId}/authorities/{authorityCode}")
    @IsAdmin
    public ResponseEntity<Void> editAccountAuthority(@RequestBody Boolean value, @PathVariable String userId, @PathVariable String authorityCode){
        this.accountService.editAccountAuthority(userId, authorityCode, value);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Delete a user's account")
    @ApiResponse(responseCode="200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    @DeleteMapping("/admin/accounts/{userId}")
    @IsAdmin
    public ResponseEntity<Void> deleteAccount( @PathVariable String userId){
        this.accountService.deleteAccount(userId);
        return ResponseEntity.ok().build();
    }
}
