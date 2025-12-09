package com.loja.autos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.service.BackblazeB2Service;

@RestController
@RequestMapping(value = "b2")
public class B2Controller {

	@Autowired
	private BackblazeB2Service backblazeService;

	@GetMapping("/link-download")
	public String getUploadUrl() {
		try {
			return backblazeService.generatePreSignedUrl("arquivo_teste");
		} catch (Exception e) {
			return "Falha ao gerar link para download " + e;
		}
	}
	
	 @GetMapping("/link-upload")
	    public ResponseEntity<String> getUploadUrl(@RequestParam String bucketId) {
	        try {
	            String urlAndToken = backblazeService.getUploadUrl(bucketId);
	            return new ResponseEntity<>(urlAndToken, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Falha ao obter URL de upload: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}