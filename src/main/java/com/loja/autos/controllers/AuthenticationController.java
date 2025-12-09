package com.loja.autos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.dto.ResponseDto;
import com.loja.autos.dto.request.AuthenticationDto;
import com.loja.autos.security.AuthenticationService;
import com.loja.autos.security.TwoFactorAuthService;
import com.loja.autos.security.UserSystem;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationService service;

	@Autowired
	private TwoFactorAuthService twoFactorAuthService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDto request) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseDto.fromData(service.login(request), HttpStatus.OK, "sucesso"));
	}
	
	@PostMapping("/generate-qr")
	public ResponseEntity<String> generateQrUrl(@RequestBody @Valid AuthenticationDto request) {
		UserSystem user = (UserSystem) service.logiBasic(request);
		String qrUrl = twoFactorAuthService.generateQRUrl(user);

		return ResponseEntity.ok(qrUrl);
	}

	@PostMapping("/verify-2fa")
	public ResponseEntity<?> verify2FA(@RequestParam("2faCode") int code2fa, @RequestBody @Valid AuthenticationDto request) {
		boolean is2FAValid = twoFactorAuthService.verify2FA(request.login(), code2fa);

		if (is2FAValid) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseDto.fromData(service.login(request), HttpStatus.OK, "sucesso"));
		}

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid 2FA Code.");
	}

	
}
