package com.ptopalidis.cecloud.platform.account.service;


import com.ptopalidis.cecloud.platform.account.domain.CECloudv2Account;
import com.ptopalidis.cecloud.platform.account.domain.CECloudv2AccountUpdateDTO;
import com.ptopalidis.cecloud.platform.account.mapper.CECloudv2AccountMapper;
import com.ptopalidis.cecloud.platform.machinefile.config.MachineFileProperties;
import com.topcode.files.service.S3Service;
import com.topcode.web.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CECloudv2AccountService {

    private final MachineFileProperties machineFileProperties;
    private final AccountService<CECloudv2Account> accountService;
    private final S3Service s3Service;
    private final CECloudv2AccountMapper ceCloudv2AccountMapper;

    public CECloudv2Account updateAccount(CECloudv2AccountUpdateDTO accountUpdateDTO, MultipartFile logo, MultipartFile signature, String userId) throws IOException {

        CECloudv2Account account = accountService.findByUserId(userId);

        if(logo != null) {
            String logos3Key = generateS3KeyFromAccount(account,logo, "logo");
            PutObjectResponse uploadFileResponse =
                    this.s3Service.uploadFile(logos3Key,logo, ObjectCannedACL.PUBLIC_READ);

            String logoUrl = s3Service.getFileUrl(logos3Key);
            account.setLogo(logoUrl);
        }

        if(signature != null) {
            String signatures3Key = generateS3KeyFromAccount(account,signature, "signature");
            PutObjectResponse uploadFileResponse =
                    this.s3Service.uploadFile(signatures3Key,signature, ObjectCannedACL.PUBLIC_READ);

            String signatureUrl = s3Service.getFileUrl(signatures3Key);
            account.setSignature(signatureUrl);
        }

        ceCloudv2AccountMapper.updateAccountFromDto(accountUpdateDTO, account);

        return accountService.saveAccount(account,userId);

    }

    private String generateS3KeyFromAccount(CECloudv2Account account, MultipartFile file, String fileName){
        return machineFileProperties.getS3directory() +
                "/" +
                account.getId() +
                "/account/" + fileName + "." + FilenameUtils.getExtension(file.getOriginalFilename());
    }
}
