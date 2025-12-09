package com.loja.autos.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Configuration
public class S3Config {
	
	@Value("${aws.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.secretAccessKey}")
    private String secretAccessKey;

	@Value("${aws.region}")
    private String region;

    @Bean
    S3Presigner s3Presigner() {
    	AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        return S3Presigner.builder()
                          .region(Region.of(region))
                          .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                          .build();
    }

    public String generatePresignedUrlForUpload(String bucketName, String keyName, S3Presigner presigner) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                                                         .bucket(bucketName)
                                                         .key(keyName)
                                                         .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                                                                        .signatureDuration(Duration.ofMinutes(2))
                                                                        .putObjectRequest(objectRequest)
                                                                        .build();

        PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(presignRequest);
        return presignedRequest.url().toString();
    }
}
