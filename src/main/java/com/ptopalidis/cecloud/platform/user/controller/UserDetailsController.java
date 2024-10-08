package com.ptopalidis.cecloud.platform.user.controller;

import com.ptopalidis.cecloud.platform.user.domain.UserDetails;
import com.ptopalidis.cecloud.platform.user.domain.dto.UpdateUserDetailsDto;
import com.ptopalidis.cecloud.platform.user.service.UserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UserDetailsController {



    private final UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @GetMapping("/user-details")
    @PreAuthorize("hasAuthority('GET_USER_DETAILS')")
    public ResponseEntity<UserDetails> getUserDetails(Authentication auth){

        if(auth instanceof JwtAuthenticationToken jwtAuthenticationToken){
            return this.userDetailsService.extractUserDetailsFromAuthContext(auth).map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/user-details/{userid}")
    public ResponseEntity<UserDetails> updateUserDetails(@PathVariable Long userid, @RequestParam(value="userDetails", required = true) UpdateUserDetailsDto userDetails,@RequestParam(value = "logo", required = false) MultipartFile logo, @RequestParam(value = "signature", required = false) MultipartFile signature) throws IOException {
        return ResponseEntity.ok(this.userDetailsService.updateUserDetails(userid,userDetails,logo,signature));
    }


}
