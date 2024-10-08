package com.ptopalidis.cecloud.platform.user.service;


import com.ptopalidis.cecloud.platform.common.exception.GlobalException;
import com.ptopalidis.cecloud.platform.common.exception.error.UserDetailsNotFoundError;
import com.ptopalidis.cecloud.platform.common.security.utils.SecurityUtils;
import com.ptopalidis.cecloud.platform.user.domain.UserDetails;
import com.ptopalidis.cecloud.platform.user.domain.dto.UpdateUserDetailsDto;
import com.ptopalidis.cecloud.platform.user.repository.UserDetailsRepository;
import com.ptopalidis.cecloud.platform.user.transform.UpdateUserDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsService {


    private final UserDetailsRepository userDetailsRepository;
    private final UpdateUserDetailsMapper updateUserDetailsMapper;
    private final SecurityUtils securityUtils;

    public Optional<UserDetails> extractUserDetailsFromAuthContext(Authentication auth){
        Long userId = securityUtils.extractUserIdFromAuthContext().orElseThrow(()->new GlobalException(new UserDetailsNotFoundError()));
        return Optional.ofNullable(this.userDetailsRepository.findByUserId(userId).stream().findFirst().orElseThrow(()-> new GlobalException(new UserDetailsNotFoundError())));
    }

    public UserDetails updateUserDetails(Long userid, UpdateUserDetailsDto updateUserDetailsDto, MultipartFile logo, MultipartFile signature) throws IOException {

        UserDetails userDetails = this.userDetailsRepository.findByUserId(userid).get(0);

        if(userDetails == null){
            throw new GlobalException(new UserDetailsNotFoundError());
        }

        Optional<String> logoUrl = Optional.empty();
        Optional<String>  signatureUrl = Optional.empty();;
        /*if(logo!= null){
            logoUrl = Optional.ofNullable(this.storageService.saveFile(logo,userid.toString() +"/account"));
        }

        if(signature != null){
            signatureUrl = Optional.ofNullable(this.storageService.saveFile(signature,userid.toString() +"/account"));
        }*/

       this.updateUserDetailsMapper.updateUserDetailsFromDto(updateUserDetailsDto,userDetails);



        if(logoUrl.isPresent()){
            userDetails.setLogo(logoUrl.get());
        }

        if(signatureUrl.isPresent()){
            userDetails.setSignature(signatureUrl.get());
        }


        userDetailsRepository.save(userDetails);

        return userDetails;

    }
}
