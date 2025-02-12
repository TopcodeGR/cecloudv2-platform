package com.ptopalidis.cecloud.platform.file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class S3Service {

    private AmazonS3 s3client;

    @Value("${do.s3.bucket}")
    private String bucketName;

    public S3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public PutObjectResult uploadFile(String keyName, MultipartFile file, Optional<CannedAccessControlList> accessControlList) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        return s3client.putObject(new PutObjectRequest(bucketName, keyName, file.getInputStream(), metadata)
                .withCannedAcl(accessControlList.orElse(CannedAccessControlList.AuthenticatedRead)));
    }

    public void deleteFile(String s3Key){
         s3client.deleteObject(bucketName,s3Key);
    }

    public String getFileUrl(String s3Key){
        return s3client.getUrl(bucketName,s3Key).toString();
    }

    public S3Object getFile(String keyName) {
        return s3client.getObject(bucketName, keyName);
    }

}