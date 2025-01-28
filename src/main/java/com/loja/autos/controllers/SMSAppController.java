package com.loja.autos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.autos.dto.request.SmsRequestDto;
import com.loja.autos.service.SMSAppService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("sms")
public class SMSAppController {

    @Autowired
    private SMSAppService smsAppService;

    @PostMapping("/send")
    public String sendMessage(@RequestBody @Valid SmsRequestDto request) {
        smsAppService.sendSMSAppMessage(request);
        return "Mensagem enviada com sucesso para: " + request.to();
    }
}
