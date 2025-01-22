package com.loja.autos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.service.S3Service;

@RestController
@RequestMapping(value = "s3")
public class S3Controller {

	@Autowired
	private S3Service s3Service;

	@GetMapping("/link")
    public String generatePresignedUrl() {
    	String presignedUrl = s3Service.generatePresignedUrlForUpload("meu-bucket", "arquivo.txt");
        return "Presigned URL gerada com sucesso: " + presignedUrl;
    }
}
