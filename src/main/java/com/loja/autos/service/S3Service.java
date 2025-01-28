package com.loja.autos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.time.Duration;

@Service
public class S3Service {

    @Autowired
    private S3Presigner s3Presigner;

    public String generatePresignedUrlForUpload(String bucketName, String keyName) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                                                         .bucket(bucketName)
                                                         .key(keyName)
                                                         .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                                                                        .signatureDuration(Duration.ofMinutes(10))
                                                                        .putObjectRequest(objectRequest)
                                                                        .build();

        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);
        return presignedRequest.url().toString();
    }
}
